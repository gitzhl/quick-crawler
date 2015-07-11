package com.stara.crawler.swt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.stara.crawler.swt.utils.SwingConsole;

public class Button2 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8559773976572180593L;
	private JButton
			b1 = new JButton("Button A"),
			b2 = new JButton("Button B");
	private JTextField jTextField = new JTextField(10);
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String name = ((JButton)e.getSource()).getText();
			jTextField.setText(name);
		}
	}
	private ButtonListener buttonListener = new ButtonListener();
	public Button2(){
		b1.addActionListener(buttonListener);
		b2.addActionListener(buttonListener);
		setLayout(new FlowLayout());
		add(b1);
		add(b2);
		add(jTextField);
	}
	public static void main(String[] args) {
		SwingConsole.run(new Button2(),200, 150);
	}
}
