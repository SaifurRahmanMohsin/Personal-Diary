/**
 * 
 */
package com.tempestronics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tempestronics.helpers.DatabaseHandler;

public abstract class AppPanel extends DatabaseHandler {
	JPanel topPanel, mainPanel, bottomPanel;
	Launcher parent;
	
	public AppPanel(Launcher parent) {
		this(parent, true);
	}
	
	public AppPanel(Launcher parent, boolean isStatusEnabled) {
		this.parent = parent;
		topPanel = new JPanel();
		mainPanel = new JPanel();
		bottomPanel = new JPanel();
		try {
			initTopPanel();
			initMainPanel();
			if(isStatusEnabled)
				bottomPanel.add(parent.status);
			initBottomPanel();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(parent, ex.getMessage());
		}
	}
	
	protected abstract void initTopPanel() throws Exception ;
	protected abstract void initMainPanel() throws Exception ;
	protected abstract void initBottomPanel() throws Exception ;
	
	public JPanel getTopPanel() {
		return topPanel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}
}
