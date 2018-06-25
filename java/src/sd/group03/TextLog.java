package sd.group03;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

public class TextLog extends JPanel{
	
	private static TextLog instance; //Create the one instance of the Singleton Text Log
	private int lineNumber = 1;
	
	synchronized public static TextLog getInstance() {
		    if (TextLog.instance == null) {
		    	TextLog.instance = new TextLog ();
		    }
		    return TextLog.instance;
	}

	JTextArea textArea;
	
	private TextLog(){
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
		
		textArea = new JTextArea(7,1);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);

		clean();

		this.setLayout(gl_textLog);
		
		// end GroupLayout on TextLogger
	}
	
	synchronized public void write(String input){
		String newLog = "\n" + lineNumber + " > " + input; 
		textArea.append(newLog);
		lineNumber++;
	}
	
	synchronized public void clean(){
		textArea.setText("  Welcome on board.");
		lineNumber = 1;
	}
}
