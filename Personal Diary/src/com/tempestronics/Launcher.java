package com.tempestronics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Launcher extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static final String UPDATE_LOCATION = "http://tempestronics.com/projects/personal-diary/update.json";
	public static final String CURRENT_VERSION = "1.0.0.0";
	
	JPanel topPanel, mainPanel, bottomPanel, temp; // To store the current panel
	JLabel status = new JLabel("Status: Initializing");

	public void setTopPanel(JPanel topPanel) throws Exception {
		if(topPanel == null)
			throw new Exception("Null Panel Exception");
		add(topPanel, BorderLayout.NORTH);
		this.topPanel = topPanel;
	}

	public void setMainPanel(JPanel mainPanel) throws Exception {
		if(mainPanel == null)
			throw new Exception("Null Panel Exception");
		add(mainPanel, BorderLayout.CENTER);
		this.mainPanel = mainPanel;
	}

	public void setBottomPanel(JPanel bottomPanel) throws Exception {
		if(bottomPanel == null)
			throw new Exception("Null Panel Exception");
		temp = new JPanel(new GridLayout(2,1));
		temp.setBorder(new EmptyBorder(0, 0, 20, 0));
		temp.add(new JSeparator());
		temp.add(bottomPanel);
	    add(temp, BorderLayout.SOUTH);
		this.bottomPanel = bottomPanel;
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
			cleanPanels();
			this.setTopPanel(page.getTopPanel());
			this.setMainPanel(page.getMainPanel());
			this.setBottomPanel(page.getBottomPanel());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void cleanPanels() {
		cleanPanel(topPanel);
		cleanPanel(mainPanel);
		cleanPanel(bottomPanel);
	}

	public void cleanPanel(JPanel panel) {
		if(panel != null)
		{
			panel.removeAll();
			panel.revalidate();
			panel.repaint();
		}
	}

}
