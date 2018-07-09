package sd.group03.gui;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputForm extends JPanel implements ActionListener {
	JButton testButton;
	JTextField textField;
	private static String lastOpenedFilePath = "";
	

	InputForm(){
		this.setBackground(Color.WHITE);

		// Initialize the Open File-Button
		testButton = new JButton("Datei öffnen");
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
		JLabel lblEnterFilePath = new JLabel("Dateipfad eingeben: ");
		
		
		// Place Components of InputForm
		GroupLayout gl_inputForm = new GroupLayout(this);
		gl_inputForm.setHorizontalGroup(
			gl_inputForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputForm.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnterFilePath)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE)
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
	
    public static void setlastOpenedFilePath (String input) {
    	lastOpenedFilePath = input;
    }
    
    public static String getlastOpenedFilePath() {
    	return lastOpenedFilePath;
    }

	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == this.testButton){
			handleInput();			
		}
	}
	
	public void handleInput(){
		
		//Create a file chooser
		final JFileChooser fc = new JFileChooser();
		FileFilterArff ffa = new FileFilterArff();
		fc.setFileFilter(ffa);
		
		String pathToFile = null;
		String InTextField = textField.getText();
		
		if(InTextField.equals("Bitte geben Sie hier den Pfad zur Datei ein...") || InTextField.equals(lastOpenedFilePath) ){
			int returnVal = fc.showOpenDialog(testButton);
				
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            pathToFile = file.getAbsolutePath();
	            String message = "Es wird versucht die Datei (\"" + file.getName() + "\") zu öffnen...";
				TextLog.getInstance().write(message);
				textField.setText(pathToFile);
	        } else {
	        	String message = "Es wurde keine Datei vom Nutzer ausgewählt...";
	            TextLog.getInstance().write(message);
	        }
		} else {
			String message = "Es wird versucht die Datei (\"" + InTextField + "\") zu öffnen...";
			TextLog.getInstance().write(message);
			pathToFile = InTextField;
		}
		lastOpenedFilePath = pathToFile;
        new AISLinePicker(pathToFile);
	}
}
