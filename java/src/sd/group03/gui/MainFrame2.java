package sd.group03.gui;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainFrame2 implements ActionListener, ItemListener{
	
	public static void main(String[] args) {

        try {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        MainFrame2 window = new MainFrame2();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
        
    }
	
	// Declare Elements in GUI
	private JFrame frame;
	InputForm inputForm;
	MapView mapView; 
	TextLog textLog;
	public static DropDownBox comboBox;
	JMenuBar menuBar;
	JMenu connection, reset, credits;
	JMenuItem connectionItem, resetItem, creditsItem;
	public static JCheckBox checkBoxHistoric;
	public static JCheckBox checkBoxAgents;
	public static final int FRAME_SIZE_HEIGHT = 1000;
	public static final int FRAME_SIZE_WIDTH = 800;
	public static int mapSelected = 0;

	/**
	 * Create the application.
	 */
	public MainFrame2() {
		initialize();
		frame.setVisible(true);
		
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
		
		frame.setTitle("Schiffe versenken");		
		frame.setBounds(cornerX, cornerY, FRAME_SIZE_WIDTH, FRAME_SIZE_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		// Add the top menu bar
		menuBar = new JMenuBar();
		connection = new JMenu("Verbindung");
		connectionItem = new JMenuItem("Einstellen der Verbindung zum Broker");
		connection.add(connectionItem);
		menuBar.add(connection);
		connectionItem.addActionListener(this);
		reset = new JMenu("Zurücksetzen");
		resetItem = new JMenuItem("Programm auf Start zurücksetzen");
		reset.add(resetItem);
		menuBar.add(reset);
		resetItem.addActionListener(this);
		credits = new JMenu("Über uns");
		creditsItem = new JMenuItem("Team");
		credits.add(creditsItem);
		menuBar.add(credits);
		creditsItem.addActionListener(this);
		frame.setJMenuBar(menuBar);
		
		
		inputForm = new InputForm();
		
		textLog = TextLog.getInstance();
		
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		
		comboBox = new DropDownBox();
		checkBoxHistoric = new JCheckBox("Historische Daten");
		checkBoxAgents = new JCheckBox("Agentengebiete");
		checkBoxHistoric.setEnabled(false);
		checkBoxAgents.setEnabled(false);
		checkBoxHistoric.addItemListener(this);
		checkBoxAgents.addItemListener(this);

		
		mapView = MapView.getInstance();
		mapView.setBackground(UIManager.getColor("ComboBox.selectionBackground"));

		
		// Automatically generated code by groupGrid in Design-Mode
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(mapView, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
						.addComponent(textLog, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 500, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(checkBoxHistoric,  GroupLayout.DEFAULT_SIZE, 137, GroupLayout.PREFERRED_SIZE)
										.addComponent(checkBoxAgents,  GroupLayout.DEFAULT_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
						.addComponent(inputForm, GroupLayout.PREFERRED_SIZE, 774, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(inputForm, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textLog, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
									.addComponent(checkBoxHistoric, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addComponent(checkBoxAgents, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
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
    	
    	// Reset the content of the frame
    	if(resetItem == source){
    		TextLog.getInstance().clean();
    		// Add more reset functionality, e.g. in map:
    		// MapView.getInstance().changeMap(0);
    		comboBox.setSelectedIndex(0);
    		inputForm.textField.setText("");
    	}
    	
    	// Return the credits for the work
    	if(creditsItem == source){
    		TextLog.getInstance().write("Dieses Programm wurde konzipiert, implementiert und trainiert von Arne Gruenhagen, Thilo Fischer und Hauke Diers.");
    	}
    	
    	// Open a new frame to enter the IP and Port of the Broker
    	if(connectionItem == source){
    		new ConnectionSetterFrame();
    	}
    }

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		Object source = e.getItemSelectable();
		
		
		if (source == checkBoxHistoric){
			if(ItemEvent.SELECTED == e.getStateChange()){
				mapSelected += 2;
			} else {
				//DESELECTED
				mapSelected -= 2;
			}
		} else if ( source == checkBoxAgents){
			
			if(ItemEvent.SELECTED == e.getStateChange()){
				mapSelected += 4;
			} else {
				//DESELECTED
				mapSelected -= 4;
			}
		}
		mapView.changeMap(mapSelected);
		mapView.drawlivePoints();

	}
}
