package com.stara.crawler.swt.utils;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingConsole {
	public static void run(final JFrame jFrame,final int width,final int height){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				jFrame.setTitle(jFrame.getClass().getSimpleName());
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jFrame.setSize(width,height);
				jFrame.setVisible(true);
			}
		});
	}
}
