package com.stara.crawler.swt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.stara.crawler.swt.utils.SwingConsole;

public class ShowAddListeners extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3978889413914292316L;
	 
	private JTextField name = new JTextField(25);
	private JTextArea results = new JTextArea(40,65);
	
	private static Pattern addListener = Pattern.compile("(add\\w+?Listener\\(.*?\\))");
	private static Pattern qualifier = Pattern.compile("\\w+\\.");
	class NameL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String nm = name.getText().trim();
			if(nm.length() == 0){
				results.setText("No Match");
				return;
			}
			Class<?> kind;
			try {
				kind = Class.forName("javax.swing."+nm);
			} catch (ClassNotFoundException e1) {
				results.setText("No Match");
				return;
			}
			Method [] methods = kind.getMethods();
			results.setText("");
			for(Method method:methods){
				Matcher matcher = addListener.matcher(method.toString());
				if(matcher.find()){
					results.append(qualifier.matcher(matcher.group(1)).replaceAll("")+"\n");
				}
			}
		}
		
	}

	public ShowAddListeners(){
		NameL nameListener = new NameL();
		name.addActionListener(nameListener);
		JPanel top = new JPanel();
		top.add(new JLabel("Swing class name (press Enter)"));
		top.add(name);
		add(BorderLayout.NORTH,top);
		add(new JScrollPane(results));
		name.setText("JText Area");
		nameListener.actionPerformed(new ActionEvent("", 0, ""));
	}
	public static void main(String[] args) {
		SwingConsole.run(new ShowAddListeners(), 500, 400);
	}

}
