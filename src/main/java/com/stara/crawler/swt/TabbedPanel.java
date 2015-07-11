package com.stara.crawler.swt;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.stara.crawler.swt.utils.SwingConsole;

public class TabbedPanel extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6241699477438208825L;
	private String [] flavors = {
			"Chocolate","Strawberry","Vanilla Fudge Swirl",
			"Mint Chip","Mocha Almond Fudge","Rum Raisin",
			"Praline Cream","Mud Pie"
	};
	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField(20);
	
	public TabbedPanel(){
		int i = 0;
		for (String flavor:flavors) {
			tabs.addTab(flavors[i], new JButton("Tabbed panle "+i++));
		}
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txt.setText("Tab selected: "+tabs.getSelectedIndex());
			}
		});
		add(BorderLayout.SOUTH,txt);
		add(tabs);
	}
	public static void main(String[] args) {
		SwingConsole.run(new TabbedPanel(), 400, 250);
	}
}
