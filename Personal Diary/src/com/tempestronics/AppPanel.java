/**
 * 
 */
package com.tempestronics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AppPanel {
	JPanel topPanel, mainPanel, bottomPanel;
	JFrame parent;
	
	public AppPanel(JFrame parent) {
		this.parent = parent;
		topPanel = new JPanel();
		mainPanel = new JPanel();
		bottomPanel = new JPanel();
	}
	
	protected abstract JPanel initTopPanel() throws Exception ;
	protected abstract JPanel initMainPanel() throws Exception ;
	protected abstract JPanel initBottomPanel() throws Exception ;
	
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
