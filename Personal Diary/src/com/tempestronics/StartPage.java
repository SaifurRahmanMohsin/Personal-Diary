package com.tempestronics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartPage extends AppPanel implements ActionListener {
	
	final static RoundButton btn = new RoundButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("btn.png")), "btnd.png", "btng.png");
	
	public StartPage(Launcher parent) {
		super(parent);
		btn.addActionListener(this);
	}

	public void initTopPanel() {
		JLabel jlWelcome = new JLabel("Welcome to Personal Diary", JLabel.CENTER);
	    jlWelcome.setBorder(new EmptyBorder(20, 0, 20, 0));
	    jlWelcome.setFont(new Font("Courier New", Font.BOLD, 35));
	    topPanel.add(jlWelcome, BorderLayout.NORTH);
	}

	public void initMainPanel() throws UnsupportedEncodingException {
		mainPanel.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("diary.png"));
	    JLabel jlLogo = new JLabel(icon);
		
		mainPanel.add(jlLogo, BorderLayout.CENTER);
		JPanel btnLogin = new JPanel(new FlowLayout());
		btnLogin.add(btn);
		mainPanel.add(btnLogin, BorderLayout.SOUTH);
	}

	public void initBottomPanel() {}

	@Override
	public void actionPerformed(ActionEvent e) {
		LoginPanel login = new LoginPanel(parent);
		parent.setLauncherPanel(login);
	}
}
