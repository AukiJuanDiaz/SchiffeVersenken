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

public class MainFrame2 implements ActionListener {

	private JFrame frame;
	JButton testButton;
	JPanel inputForm;
	JPanel mapView; 
	JPanel textLog;
	JLabel label;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inputForm = new JPanel();
		inputForm.setBackground(Color.WHITE);
		
		mapView = new JPanel();
		mapView.setBackground(Color.GREEN);
		
		textLog = new JPanel();
		textLog.setBackground(Color.DARK_GRAY);
		
		
		
		
		
		
		
		
		System.out.println("Eclipse nervt");
		
		
		
		
		
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(mapView, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textLog, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
						.addComponent(inputForm, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
		// Initialize elements
		testButton = new JButton("Hallo, Arne");
		testButton.setBackground(Color.WHITE);
		testButton.addActionListener(this); 
		
		label = new JLabel();
		
		inputForm.add(testButton);
		inputForm.add(label);
	}
	
	public void actionPerformed(ActionEvent ae) {
		System.out.println("test");
		if(ae.getSource() == this.testButton){
			label.setText("Hallo, Hauke");
		}
	}
}
