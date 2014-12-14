package com.tempestronics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Launcher extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel prevTopPanel, prevMainPanel, prevBottomPanel; // To store previous panel
	JPanel topPanel, mainPanel, bottomPanel; // To store the current panel

	public void setTopPanel(JPanel topPanel) throws Exception {
		if(topPanel == null)
			throw new Exception("Null Panel Exception");
		add(topPanel, BorderLayout.NORTH);
		this.prevTopPanel = this.topPanel;
		this.topPanel = topPanel;
	}

	public void setExTopPanel() throws Exception {
		if(prevTopPanel == null)
			throw new Exception("Null Panel Exception");
		this.topPanel = prevTopPanel;
	}

	public void setMainPanel(JPanel mainPanel) throws Exception {
		if(mainPanel == null)
			throw new Exception("Null Panel Exception");
		add(mainPanel, BorderLayout.CENTER);
		this.prevMainPanel = this.mainPanel;
		this.mainPanel = mainPanel;
	}

	public void setExMainPanel() throws Exception {
		if(prevMainPanel == null)
			throw new Exception("Null Panel Exception");
		this.mainPanel = prevMainPanel;
	}

	public void setBottomPanel(JPanel bottomPanel) throws Exception {
		if(bottomPanel == null)
			throw new Exception("Null Panel Exception");
		JPanel temp = new JPanel(new GridLayout(2,1));
		temp.setBorder(new EmptyBorder(0, 0, 20, 0));
		temp.add(new JSeparator());
		temp.add(bottomPanel);
	    add(temp, BorderLayout.SOUTH);
		this.prevBottomPanel = this.bottomPanel;
		this.bottomPanel = bottomPanel;
	}

	public void setExBottomPanel() throws Exception {
		if(prevBottomPanel == null)
			throw new Exception("Null Panel Exception");
		this.bottomPanel = prevBottomPanel;
	}

	Dimension screenSize;
	int diaryHeight, diaryWidth;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher frame = new Launcher();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}

	public Launcher() throws Exception
	{
		super("My Diary");
		
		// Get Screen Dimensions & initialize frame
		Toolkit kit = Toolkit.getDefaultToolkit();
		screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		diaryWidth = 4 * screenWidth / 5;
		diaryHeight = 4 * screenHeight / 5;
		setSize(diaryWidth, diaryHeight);
		
		// Set the frame icon
	    Image icon = Toolkit.getDefaultToolkit().getImage("diary.png");
	    setIconImage(icon);
		
		// Set a 3-panel layout
		StartPage start = new StartPage(this);
		setLauncherPanel(start);
	}

	public void setLauncherPanel(AppPanel page) {
		try {
			this.setTopPanel(page.getTopPanel());
			this.setMainPanel(page.getMainPanel());
			this.setBottomPanel(page.getBottomPanel());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

}
