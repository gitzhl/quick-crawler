package com.stara.crawler.swt;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.stara.crawler.swt.utils.SwingConsole;

public class Button1 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8273673394259170134L;
	private JButton 
			b1 = new JButton("Button1"),
			b2 = new JButton("Button2");
	public Button1(){
		setLayout(new FlowLayout());
		add(b1);
		add(b2);
	}
	public static void main(String[] args) {
		SwingConsole.run(new Button1(), 200,100);
	}
}
