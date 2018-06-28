package sd.group03.gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class ConnectionSetterFrame implements ActionListener {
	private JFrame connectionFrame;
	public static final int CONNECTION_FRAME_SIZE_HEIGHT = 140;
	public static final int CONNECTION_FRAME_SIZE_WIDTH = 300;
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JButton submitButton;
	private String IP = GUINetworkConnection.getHost();
	private short port = GUINetworkConnection.getPort();
	
	ConnectionSetterFrame(){
		connectionFrame = new JFrame();
		connectionFrame.setVisible(true);
		connectionFrame.setResizable(false);
		connectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		connectionFrame.setTitle("Verbindung zum Broker");
		connectionFrame.setSize(CONNECTION_FRAME_SIZE_WIDTH, CONNECTION_FRAME_SIZE_HEIGHT);
		
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
		
		GroupLayout groupLayout = new GroupLayout(connectionFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(labelIP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelPort, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldIP)
						.addComponent(textFieldPort, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
					.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(submitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(70))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelIP)
						.addComponent(textFieldIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelPort)
						.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(submitButton)
					.addContainerGap(16, Short.MAX_VALUE))
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
		TextLog.getInstance().write("Neue Verbindung zum Broker mit der IP: " + IP + " und Port: " + String.valueOf(port) + " wird gesetzt.");
		connectionFrame.setVisible(false);
		connectionFrame.dispose();
	}
}
