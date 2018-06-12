package sd.group03;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

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
import javax.swing.KeyStroke;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.UIManager;

public class MainFrame2 implements ActionListener{
	
	// Declare Elements in GUI
	private JFrame frame;
	InputForm inputForm;
	JPanel mapView; 
	TextLog textLog;
	JMenuBar menuBar;
	JMenu reset, credits;
	JMenuItem resetItem, creditsItem;
	public static final int FRAME_SIZE_HEIGHT = 800;
	public static final int FRAME_SIZE_WIDTH = 800;
	
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
		
		// Initialize frame and the three panels
		// Calculate Frame position
		// Assumption: Screen is big enough for 800 x 800
		int screenSizeHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int screenSizeWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int cornerX = (screenSizeWidth / 2) - (FRAME_SIZE_WIDTH / 2);
		int cornerY = (screenSizeHeight / 2) - (FRAME_SIZE_HEIGHT / 2);
		
		frame = new JFrame();
		
		frame.setBounds(cornerX, cornerY, FRAME_SIZE_WIDTH, FRAME_SIZE_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// Add the top menu bar
		menuBar = new JMenuBar();
		reset = new JMenu("Reset");
		resetItem = new JMenuItem("Reset the program.");
		reset.add(resetItem);
		menuBar.add(reset);
		resetItem.addActionListener(this);
		credits = new JMenu("Credits");
		creditsItem = new JMenuItem("And the winner is...");
		credits.add(creditsItem);
		menuBar.add(credits);
		creditsItem.addActionListener(this);
		frame.setJMenuBar(menuBar);
		
		
		inputForm = new InputForm();
		
		mapView = new JPanel();
		mapView.setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		
		textLog = TextLog.getInstance();
		
		
		
		
		// Automatically generated code by groupGrid in Design-Mode
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(mapView, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
						.addComponent(textLog, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
						.addComponent(inputForm, GroupLayout.PREFERRED_SIZE, 774, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(inputForm, GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textLog, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(mapView, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		//End of Group Layout for frame
		

	} //End of initialize
	
    public void actionPerformed(ActionEvent e) {
    	// Function to handle the events in the menu bar
    	JMenuItem source = (JMenuItem)(e.getSource());
    	
    	if(resetItem == source){
    		TextLog.getInstance().clean();
    	}
    	
    	// Return the credits for the work
    	if(creditsItem == source){
    		TextLog.getInstance().write("This application was designed, trained and implemented by Arne Grünhagen, Thilo Fischer and Hauke Diers.");
    	}
    }
}
