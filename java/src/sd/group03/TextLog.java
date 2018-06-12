package sd.group03;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;

public class TextLog extends JPanel{
	
	TextLog(){
		this.setBackground(Color.DARK_GRAY);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVisible(true);
		
		// Begin GroupLayout on TextLogger
		GroupLayout gl_textLog = new GroupLayout(this);
		gl_textLog.setHorizontalGroup(
			gl_textLog.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
		);
		gl_textLog.setVerticalGroup(
			gl_textLog.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
		);
		
		JTextArea textArea = new JTextArea(7,1);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		
		textArea.setText("========= Ahoi du Landratte! =========");
		this.setLayout(gl_textLog);
		
		// end GroupLayout on TextLogger
		
		
		
	}
	
	
	public void write(String input){
		
	}
	
	

}
