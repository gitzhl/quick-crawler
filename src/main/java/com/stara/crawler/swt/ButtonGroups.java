package com.stara.crawler.swt;

import java.awt.FlowLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import com.stara.crawler.swt.utils.SwingConsole;

public class ButtonGroups extends JFrame{
	
	private static final long serialVersionUID = 8137640429275607858L;
	
	private String [] ids = {
			"June","Ward","Beaver","Wally","Eddie","Lumpy"
	};
	
	static JPanel makeBPanel(
		Class<? extends AbstractButton> kind,String [] ids){
		ButtonGroup bg = new ButtonGroup();
		JPanel jp = new JPanel();
		String title = kind.getName();
		title = title.substring(title.lastIndexOf(".")+1);
		jp.setBorder(new TitledBorder(title));
		for (String id:ids) {
			AbstractButton ab = new JButton("failed");
			try {
				Constructor<?> ctor = kind.getConstructor(String.class);
				ab = (AbstractButton)ctor.newInstance(id);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			bg.add(ab);
			jp.add(ab);
		}
		return jp;
	}
	public ButtonGroups(){
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(makeBPanel(JButton.class, ids));
		add(makeBPanel(JToggleButton.class, ids));
		add(makeBPanel(JCheckBox.class, ids));
		add(makeBPanel(JRadioButton.class, ids));
	}
	public static void main(String[] args) {
		SwingConsole.run(new ButtonGroups(), 500, 350);
	}
}
