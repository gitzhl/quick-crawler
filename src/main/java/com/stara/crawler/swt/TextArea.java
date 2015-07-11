package com.stara.crawler.swt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.stara.crawler.swt.utils.SwingConsole;

public class TextArea extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3604942295204811760L;

	private JButton
			addBtn = new JButton("Add Data"),
			clearBtn = new JButton("Clear Data");
	
	private JTextArea jTextArea = new JTextArea(20,40);
	private Map<String, String> map = new HashMap<String, String>();
	
	public TextArea(){
		map.put("001", "湖南");
		map.put("002", "广东");
		map.put("003", "广西");
		map.put("004", "湖北");
		map.put("005", "河南");
		map.put("006", "河北");
		map.put("007", "云南");
		map.put("008", "海南");
		map.put("009", "四川");
		map.put("010", "湖南");
		map.put("011", "广东");
		map.put("012", "广西");
		map.put("013", "湖北");
		map.put("014", "河南");
		map.put("015", "河北");
		map.put("016", "云南");
		map.put("017", "海南");
		map.put("018", "四川");
		map.put("019", "湖南");
		map.put("020", "广东");
		map.put("021", "广西");
		map.put("022", "湖北");
		map.put("023", "河南");
		map.put("024", "河北");
		map.put("025", "云南");
		map.put("026", "海南");
		map.put("027", "四川");
		map.put("028", "湖南");
		map.put("029", "广东");
		map.put("030", "广西");
		map.put("031", "湖北");
		map.put("032", "河南");
		map.put("033", "河北");
		map.put("034", "云南");
		map.put("035", "海南");
		map.put("036", "四川");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Map.Entry<String, String> me:map.entrySet()) {
					jTextArea.append(me.getKey()+":"+me.getValue()+"\n");
				}
			}
		});
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextArea.setText("");
			}
		});
		setLayout(new FlowLayout());
		add(new JScrollPane(jTextArea));
		add(addBtn);
		add(clearBtn);
	}
	public static void main(String[] args) {
		SwingConsole.run(new TextArea(), 475, 425);
	}
}	
