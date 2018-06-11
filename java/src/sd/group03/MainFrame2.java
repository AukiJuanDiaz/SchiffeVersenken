package sd.group03;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame2 implements ActionListener {
	
	// Declare Elements in GUI
	private JFrame frame;
	JButton testButton;
	JPanel inputForm;
	JPanel mapView; 
	JPanel textLog;
	JTextField textField;
	String FilePathInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame2 window = new MainFrame2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// To DO: Set Sizes of the window, frame, dynamically?
		// Initialize frame and the three panels
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inputForm = new JPanel();
		inputForm.setBackground(Color.WHITE);
		
		mapView = new JPanel();
		mapView.setBackground(Color.GREEN);
		
		textLog = new JPanel();
		textLog.setBackground(Color.DARK_GRAY);
		
		// Initialize the Open File-Button
		testButton = new JButton("Open File");
		testButton.setBackground(Color.WHITE);
		testButton.addActionListener(this);
		
		// Initialize the text input field
		textField = new JTextField();
		textField.setColumns(8);
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
		
		// Automatically generated code by groupGrid in Design-Mode
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(inputForm, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textLog, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
						.addComponent(mapView, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(inputForm, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(mapView, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textLog, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
		GroupLayout gl_inputForm = new GroupLayout(inputForm);
		gl_inputForm.setHorizontalGroup(
			gl_inputForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputForm.createSequentialGroup()
					.addGroup(gl_inputForm.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputForm.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEnterFilePath))
						.addGroup(gl_inputForm.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_inputForm.createSequentialGroup()
							.addContainerGap()
							.addComponent(testButton)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_inputForm.setVerticalGroup(
			gl_inputForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputForm.createSequentialGroup()
					.addGap(43)
					.addComponent(lblEnterFilePath)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(testButton)
					.addContainerGap(128, Short.MAX_VALUE))
		);
		inputForm.setLayout(gl_inputForm);
		// End of Automatically generated code by groupGrid in Design-Mode
	} //End of initialize
	
	
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == this.testButton){
			handleInput();			
		}
	}
	
	public void handleInput(){
		FilePathInput = textField.getText();
		System.out.println(FilePathInput);
	}
}
