package sd.group03;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InputForm extends JPanel implements ActionListener {
	JButton testButton;
	JTextField textField;

	InputForm(){
		this.setBackground(Color.WHITE);

		// Initialize the Open File-Button
		testButton = new JButton("Open File");
		testButton.setBackground(Color.WHITE);
		testButton.addActionListener(this);
		
		
		// Initialize the text input field
		textField = new JTextField();
		textField.setColumns(8);
		
		new TextHint(textField, "Bitte geben Sie hier den Pfad zur Datei ein...");
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					handleInput();			
				}
			}
		});

		// Create a label in inputForm-Panel
		JLabel lblEnterFilePath = new JLabel("Enter file path: ");
		
		
		// Place Components of InputForm
		GroupLayout gl_inputForm = new GroupLayout(this);
		gl_inputForm.setHorizontalGroup(
			gl_inputForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputForm.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnterFilePath)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 565, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(testButton)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_inputForm.setVerticalGroup(
			gl_inputForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputForm.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inputForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterFilePath)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(testButton))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		this.setLayout(gl_inputForm);
		// End of Automatically generated code by groupGrid in Design-Mode
	}

	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == this.testButton){
			handleInput();			
		}
	}
	
	public void handleInput(){
		String input = textField.getText();
		if(input.isEmpty()){
			TextLog.getInstance().write("");
		} else {
			
			// Test for MapView
			if (input.contains("change 0"))
				MapView.getinstance().changeMap(0);
			if (input.contains("change 1"))
				MapView.getinstance().changeMap(1);
			if (input.contains("change 2"))
				MapView.getinstance().changeMap(2);
			if (input.contains("make point"))
				MapView.getinstance().drawlivePoint(50,50,0xC71585);
			
			// Tests for MapView
			
			
			String message = "Try to send file (\"" + input + "\") to Broker ...";
			TextLog.getInstance().write(message);

			try {
                (new Thread(new GUINetworkConnection("127.0.0.1", (short) 9812, input))).start();
            }
            catch (IOException e) {
			    e.printStackTrace();
            }
		}
	}
}
