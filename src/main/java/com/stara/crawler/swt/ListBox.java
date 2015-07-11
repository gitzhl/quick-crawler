package com.stara.crawler.swt;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.stara.crawler.swt.utils.SwingConsole;

public class ListBox extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6271184210566217662L;
	
	private String [] flavors = {
			"Chocolate","Strawberry","Vanilla Fudge Swirl",
			"Mint Chip","Mocha Almond Fudge","Rum Raisin",
			"Praline Cream","Mud Pie"
	};
	
	private DefaultListModel<String> lItems = new DefaultListModel<String>();
	
	private JList<String> lst = new JList<String>(lItems);
	
	private JTextArea t = new JTextArea(flavors.length,20);
	
	private JButton b = new JButton("Add Item");
	
	private ActionListener bl = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(count<flavors.length){
				lItems.add(0, flavors[count++]);
			}else {
				b.setEnabled(false);
			}
		}
	};
	private ListSelectionListener ll = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting())return;
			t.setText("");
			for (Object item:lst.getSelectedValuesList()) {
				t.append(item+"\n");
			}
		}
	};
	private int count = 0;
	public ListBox(){
		t.setEditable(false);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK);
		lst.setBorder(brd);
		for (int i = 0; i < 4; i++) {
			lItems.addElement(flavors[count++]);
		}
		add(t);
		add(new JScrollPane(lst));
		add(b);
		lst.addListSelectionListener(ll);
		b.addActionListener(bl);
	}
	public static void main(String[] args) {
		SwingConsole.run(new ListBox(), 250, 375);
	}
}
