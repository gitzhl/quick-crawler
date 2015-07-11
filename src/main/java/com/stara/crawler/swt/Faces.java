package com.stara.crawler.swt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.stara.crawler.swt.utils.SwingConsole;

public class Faces extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4923979170177408261L;
	private static Icon [] faces;
	private JButton jb,jb2 = new JButton("Disable");
	private boolean mad = false;
	public Faces(){
		faces = new Icon []{
			new ImageIcon(getClass().getResource("face0.ico")),
			new ImageIcon(getClass().getResource("face1.ico")),
			new ImageIcon(getClass().getResource("face2.ico")),
			new ImageIcon(getClass().getResource("face3.ico")),
			new ImageIcon(getClass().getResource("face4.ico"))
		};
		jb = new JButton("JButton",faces[3]);
		setLayout(new FlowLayout());
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mad){
					jb.setIcon(faces[3]);
					mad = false;
				}else{
					jb.setIcon(faces[0]);
					mad = true;
				}
				jb.setVerticalAlignment(JButton.TOP);
				jb.setHorizontalAlignment(JButton.LEFT);
			}
		});
		jb.setRolloverEnabled(true);
		jb.setRolloverIcon(faces[1]);
		jb.setPressedIcon(faces[2]);
		jb.setDisabledIcon(faces[4]);
		jb.setToolTipText("Yow!");
		add(jb);
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jb.isEnabled()){
					jb.setEnabled(false);
					jb2.setText("Enable");
				}else{
					jb.setEnabled(true);
					jb2.setText("Disable");
				}
			}
		});
		add(jb2);
	}
	public static void main(String[] args) {
		SwingConsole.run(new Faces(),250, 125);
	}
	
}
