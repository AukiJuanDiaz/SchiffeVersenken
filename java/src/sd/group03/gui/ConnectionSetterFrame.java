package sd.group03.gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class ConnectionSetterFrame implements ActionListener {
	private JFrame connectionFrame;
	public static final int CONNECTION_FRAME_SIZE_HEIGHT = 140;
	public static final int CONNECTION_FRAME_SIZE_WIDTH = 300;
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JButton submitButton;
	private JRadioButton multiAgent;
	private JRadioButton singleAgent;
	private String IP = GUINetworkConnection.getHost();
	private short port = GUINetworkConnection.getPort();
	
	ConnectionSetterFrame(){
		connectionFrame = new JFrame();
		connectionFrame.setVisible(true);
		connectionFrame.setResizable(false);
		connectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		connectionFrame.setTitle("Verbindung zum Broker");
		connectionFrame.setSize(300, 180);
		
		int screenSizeHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int screenSizeWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int cornerX = (screenSizeWidth / 2) - (CONNECTION_FRAME_SIZE_WIDTH / 2);
		int cornerY = (screenSizeHeight / 2) - (CONNECTION_FRAME_SIZE_HEIGHT / 2);
		connectionFrame.setLocation(cornerX, cornerY);
		
		JLabel labelIP = new JLabel("IPv4-Adresse:");
		textFieldIP = new JTextField();
		textFieldIP.setColumns(7);
		textFieldIP.setText(IP);
		
		JLabel labelPort = new JLabel("Port-Nummer:");
		textFieldPort = new JTextField();
		textFieldPort.setColumns(7);
		textFieldPort.setText(String.valueOf(port));
		
		submitButton = new JButton("Neue Verbindung setzen");
		submitButton.addActionListener(this);
		
		multiAgent = new JRadioButton("Multi Agent-Vorhersage");
		
		singleAgent = new JRadioButton("Single Agent-Vorhersage");
		
		// Show the button as selected, which was selected for the last connection. Default multi.
		if (GUINetworkConnection.getModus()){
			multiAgent.setSelected(true);
		} else {
			singleAgent.setSelected(true);
		}

		
		ButtonGroup group = new ButtonGroup();
	    group.add(multiAgent);
	    group.add(singleAgent);
		
		GroupLayout groupLayout = new GroupLayout(connectionFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelPort, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelIP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldIP)
								.addComponent(textFieldPort, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(79)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(multiAgent, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addComponent(singleAgent, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addComponent(submitButton))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelIP))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelPort)
						.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(multiAgent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(singleAgent)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(submitButton)
					.addContainerGap())
		);
		connectionFrame.getContentPane().setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.submitButton){
			handleInput();			
		}
		
	}
	
	public void handleInput(){
		IP = textFieldIP.getText();
		port = Short.parseShort(textFieldPort.getText());
		GUINetworkConnection.setHost(IP);
		GUINetworkConnection.setPort(port);
		
		String textLogMsg = "Neue Verbindung zum Broker mit der IP: " + IP + " und Port: " + String.valueOf(port) + " wird gesetzt.";
		
		if(multiAgent.isSelected()){
			GUINetworkConnection.setModus(true);
			textLogMsg = textLogMsg + " Die Vorhersage läuft im Multi Agent-Modus.";

		} else {
			GUINetworkConnection.setModus(false);
			textLogMsg = textLogMsg + " Die Vorhersage läuft im Single Agent-Modus.";
		}
		
		TextLog.getInstance().write(textLogMsg);
		connectionFrame.setVisible(false);
		connectionFrame.dispose();
	}
}
