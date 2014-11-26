package com.tempestronics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class Launcher extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel mainPanel, currentPanel, defaultPanel;
	JLabel lblStatus;
	Dimension screenSize;
	int diaryHeight, diaryWidth;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

	public Launcher() {
		super("My Diary");
		
		// Get Screen Dimensions
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
		
		JLabel jlWelcome = new JLabel("Welcome to Personal Diary", JLabel.CENTER);
		jlWelcome.setBorder(new EmptyBorder(20, 0, 20, 0));
		jlWelcome.setFont(new Font("Courier New", Font.BOLD, 35));
		add(jlWelcome, BorderLayout.NORTH);
		
		JLabel jlLogo = new JLabel(new ImageIcon(icon));
		add(jlLogo);

		JPanel footer = new JPanel(new GridLayout(2,1));
		footer.setBorder(new EmptyBorder(0, 0, 20, 0));
		footer.add(new JSeparator());
		
		JLabel jlStatus = new JLabel("Status: Initializing");
		jlStatus.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("spinner.gif")));
		jlStatus.setFont(new Font("Consolas", Font.PLAIN, 15));
		jlStatus.setBorder(new EmptyBorder(0,20,0,0));
		footer.add(jlStatus);
		add(footer, BorderLayout.SOUTH);
		
	}
	
	

}
