package com.tempestronics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class StartPage extends AppPanel implements MouseListener {
	
	JLabel jlStatus, jlLogin;
	ImageIcon login, login_focus, login_pressed;
	final String update_location = "http://localhost/projects/PersonalDiary/update.json";
	private UpdateTask updateTask;

	public StartPage(JFrame frame) {
		super(frame);
		login = new ImageIcon(Toolkit.getDefaultToolkit().getImage("btn.png"));
	    login_focus = new ImageIcon(Toolkit.getDefaultToolkit().getImage("btnIn.png"));
	    login_pressed = new ImageIcon(Toolkit.getDefaultToolkit().getImage("btnPressed.png"));
		topPanel = new JPanel();
		mainPanel = new JPanel(new BorderLayout());
		bottomPanel = new JPanel();
		initTopPanel();
		initMainPanel();
		initBottomPanel();
		updateTask = new UpdateTask();
		updateTask.execute();
	}
	
	private class UpdateTask extends SwingWorker<Integer, String> implements PropertyChangeListener {
		
		@Override
		protected Integer doInBackground() throws Exception {
			addPropertyChangeListener(this);
			int i = 0;
			try {
				setProgress(i);
				Thread.sleep(1000);
				while(i < 99) {
					Thread.sleep(50);
					setProgress(i);
					i++;
				}
				setProgress(i);
			} catch (InterruptedException e) {
				setProgress(100);
				done();
			}
			return null;
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(evt.getNewValue() == SwingWorker.StateValue.STARTED)
				{
					jlStatus.setText("Status: Checking for updates");
					jlStatus.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("spinner.gif")));
				}
			else if(evt.getNewValue() == SwingWorker.StateValue.DONE || Integer.parseInt(evt.getNewValue().toString()) == 100)
				{
					jlStatus.setIcon(null);
					jlStatus.setText("Status: Ready");
				}
			else
				jlStatus.setText("Status: Updating - " + evt.getNewValue() + "%");
		}
		
	}

	public JPanel initTopPanel() {
		JLabel jlWelcome = new JLabel("Welcome to Personal Diary", JLabel.CENTER);
	    jlWelcome.setBorder(new EmptyBorder(20, 0, 20, 0));
	    jlWelcome.setFont(new Font("Courier New", Font.BOLD, 35));
	    topPanel.add(jlWelcome, BorderLayout.NORTH);
		return topPanel;
	}

	public JPanel initMainPanel() {
	    Image icon = Toolkit.getDefaultToolkit().getImage("diary.png");
		JLabel jlLogo = new JLabel(new ImageIcon(icon));

	    jlLogin = new JLabel(login_focus, JLabel.CENTER);
		jlLogin.addMouseListener(this);
		
		mainPanel.add(jlLogo, BorderLayout.CENTER);
		JPanel btnLogin = new JPanel(new FlowLayout());
		btnLogin.add(jlLogin);
		mainPanel.add(btnLogin, BorderLayout.SOUTH);
		return mainPanel;
	}

	public JPanel initBottomPanel() {
		this.jlStatus = new JLabel("Status: Initializing");
	    this.jlStatus.setFont(new Font("Consolas", Font.PLAIN, 15));
	    this.jlStatus.setBorder(new EmptyBorder(0,20,0,0));
	    bottomPanel.add(jlStatus);
		return bottomPanel;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(jlLogin != null) {
			jlLogin.setIcon(login_pressed);
			updateTask.cancel(true);
			//LoginPanel login = new LoginPanel();
			//launcher.setDiaryPanel(login);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(jlLogin != null)
			jlLogin.setIcon(login);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(jlLogin != null)
			jlLogin.setIcon(login);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(jlLogin != null)
			jlLogin.setIcon(login_focus);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

}
