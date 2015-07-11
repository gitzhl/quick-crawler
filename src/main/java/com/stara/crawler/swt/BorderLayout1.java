package com.stara.crawler.swt;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.stara.crawler.swt.utils.SwingConsole;

public class BorderLayout1 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3093984652877420768L;

	public BorderLayout1(){
		add(BorderLayout.EAST,new JButton("east button"));
		add(BorderLayout.WEST,new JButton("west button"));
		add(BorderLayout.NORTH,new JButton("north button"));
		add(BorderLayout.SOUTH,new JButton("south button"));
		
		add(BorderLayout.CENTER,new JButton("center button"));
	}
	public static void main(String[] args) {
		SwingConsole.run(new BorderLayout1(), 300, 250);
	}
}
