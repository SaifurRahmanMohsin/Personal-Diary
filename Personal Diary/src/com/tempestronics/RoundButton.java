package com.tempestronics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RoundButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 201412141208L;
	protected Shape shape, base;
    public RoundButton(Icon icon, String i2, String i3) {
        super(icon);
        setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(i2)));
        setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(i3)));
    }
    @Override public void updateUI() {
        super.updateUI();
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setBackground(Color.BLACK);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setAlignmentY(Component.TOP_ALIGNMENT);
        initShape();
    }
    @Override public Dimension getPreferredSize() {
        Icon icon = getIcon();
        Insets i = getInsets();
        int iw = Math.max(icon.getIconWidth(), icon.getIconHeight());
        return new Dimension(iw + i.right + i.left, iw + i.top + i.bottom);
    }
    protected void initShape() {
        if (!getBounds().equals(base)) {
            Dimension s = getPreferredSize();
            base = getBounds();
            shape = new Ellipse2D.Float(0, 0, s.width - 1, s.height - 1);
        }
    }
    @Override protected void paintBorder(Graphics g) {
        initShape();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.draw(shape);
        g2.dispose();
    }
    @Override public boolean contains(int x, int y) {
        initShape();
        return shape == null ? false : shape.contains(x, y);
    }
}