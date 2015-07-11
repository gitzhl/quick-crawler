package com.stara.crawler.swt;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.stara.crawler.swt.utils.SwingConsole;

public class FlowLayout1 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5415138075866068477L;

	public FlowLayout1(){
		setLayout(new FlowLayout());
		for (int i = 0; i < 20; i++) {
			add(new JButton("Button"+i));
		}
	}
	public static void main(String[] args) {
		SwingConsole.run(new FlowLayout1(), 300, 300);
	}
}
