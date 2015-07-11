package com.stara.crawler.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.stara.crawler.FileCrawler;
import com.stara.crawler.swt.utils.SwingConsole;

public class SWingCrawler extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5958103652923496774L;

	private JLabel label = new JLabel("文件路径");
	private JTextField textField = new JTextField(30);
	private JButton jButton = new JButton("检索");
	private JTextArea textArea = new JTextArea(20,30);
	private JButton clearBtn = new JButton("clear logs");
	
	private ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			final String path = textField.getText();
			if(!"".equals(path)){
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						FileCrawler fc = new FileCrawler(textArea);
						fc.setFileSource(path);
						fc.start();
					}
				});
			}
		}
	};
	public SWingCrawler(){
		setLayout(new FlowLayout());
		setResizable(false);
		add(label);
		add(textField);
		jButton.addActionListener(al);
		add(jButton);
		textArea.setEditable(false);
		add(new JScrollPane(textArea));
		add(clearBtn);
	}
	
	public static void main(String[] args) {
		SWingCrawler sc = new SWingCrawler();
		sc.setTitle("淘宝图片爬虫程序");
		SwingConsole.run(sc, 500, 400);
	}
	
}
