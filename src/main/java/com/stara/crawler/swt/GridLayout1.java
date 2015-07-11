package com.stara.crawler.swt;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.stara.crawler.swt.utils.SwingConsole;

public class GridLayout1 extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7096694541955088645L;
	
	public GridLayout1(){
		setLayout(new GridLayout(7, 3));
		for (int i = 0; i < 20; i++) {
			add(new JButton("Button"+i));
		}
	}
	public static void main(String[] args) {
		SwingConsole.run(new GridLayout1(), 300, 300);
	}

}
