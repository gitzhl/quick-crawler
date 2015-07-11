package com.stara.crawler.swt;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import com.stara.crawler.swt.utils.SwingConsole;

public class Buttons extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4912528108905610471L;
	
	private JButton jb = new JButton("JButton");
	
	private BasicArrowButton up = new BasicArrowButton(BasicArrowButton.NORTH),
							 down = new BasicArrowButton(BasicArrowButton.SOUTH),
							 right = new BasicArrowButton(BasicArrowButton.EAST),
							 left = new BasicArrowButton(BasicArrowButton.WEST);
	
	public Buttons(){
		setLayout(new FlowLayout());
		add(jb);
		add(new JToggleButton("JToggleButton"));
		add(new JCheckBox("JCheckBox"));
		add(new JRadioButton("JRadioButton"));
		JPanel jp = new JPanel();
		jp.setBorder(new TitledBorder("Directions"));
		jp.add(up);
		jp.add(down);
		jp.add(left);
		jp.add(right);
		add(jp);
	}
	public static void main(String[] args) {
		SwingConsole.run(new Buttons(), 350, 200);
	}
}