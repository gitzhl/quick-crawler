package com.stara.crawler.swt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.stara.crawler.swt.utils.SwingConsole;

public class TextFields extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095042454293076309L;
	
	private JButton btn1 = new JButton("Get Text"),
					btn2 = new JButton("Set Text");
	
	private JTextField t1 = new JTextField(30),
			           t2 = new JTextField(30),
			           t3 = new JTextField(30);
	
	private String s = "";
	
	private UpperCaseDocument ucd = new UpperCaseDocument();
	
	public TextFields(){
		t1.setDocument(ucd);
		ucd.addDocumentListener(new T1());
		btn1.addActionListener(new B1());
		btn2.addActionListener(new B2());
		t1.addActionListener(new T1A());
		setLayout(new FlowLayout());
		add(btn1);
		add(btn2);
		add(t1);
		add(t2);
		add(t3);
	}
	
	class T1 implements DocumentListener{
		public void insertUpdate(DocumentEvent e) {
			t2.setText(t1.getText());
			t3.setText("Text: "+t1.getText());
		}
		public void removeUpdate(DocumentEvent e) {
			t2.setText(t1.getText());
		}
		public void changedUpdate(DocumentEvent e) {
		}
	}
	
	class T1A implements ActionListener{
		private int count = 0;
		public void actionPerformed(ActionEvent e) {
			t3.setText("t1 Action Event "+count++);
		}
		
	}
	
	class B1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(t1.getSelectedText() == null){
				s = t1.getText();
			}else{
				s = t1.getSelectedText();
				t1.setEditable(true);
			}
		}
	}
	
	class B2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ucd.setUpperCase(false);
			t1.setText("Inserted By Button2"+s);
			ucd.setUpperCase(true);
			t1.setEditable(false);
		}
	}
	public static void main(String[] args) {
		SwingConsole.run(new TextFields(), 375, 200);
	}
	class UpperCaseDocument extends PlainDocument{
		private static final long serialVersionUID = 5104329667532124740L;
		private boolean upperCase = true;
		public void setUpperCase(boolean upperCase){
			this.upperCase = upperCase;
		}
		public void insertString(int offset,String str,AttributeSet attSet) throws BadLocationException{
			if(upperCase) {str = str.toUpperCase();}
			super.insertString(offset, str, attSet);
		}
	}
}
