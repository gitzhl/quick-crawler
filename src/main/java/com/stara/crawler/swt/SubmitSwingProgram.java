package com.stara.crawler.swt;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SubmitSwingProgram extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3931126481249733310L;
	JLabel jLabel;
	public SubmitSwingProgram(){
		super("Quick");
		jLabel = new JLabel("A Label");
		add(jLabel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,100);
		setVisible(true);
	}
	static SubmitSwingProgram ssProgram;
	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ssProgram = new SubmitSwingProgram();
			}
		});
		TimeUnit.SECONDS.sleep(1);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ssProgram.jLabel.setText("this is new Lable!");
			}
		});
	}
}
