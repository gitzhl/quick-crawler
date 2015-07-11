package com.stara.crawler.swt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.stara.crawler.swt.utils.SwingConsole;

public class TextPane extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8997939670739926309L;
	
	private JButton b = new JButton("Add Text");
	
	private JTextPane tp = new JTextPane();
	
	public TextPane(){
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 10; i++) {
					tp.setText(tp.getText()+System.currentTimeMillis()+"\n");
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new JScrollPane(tp));
		add(BorderLayout.SOUTH,b);
	}
	public static void main(String[] args) {
		SwingConsole.run(new TextPane(), 475, 425);
	}
}
