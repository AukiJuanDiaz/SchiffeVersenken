package sd.group03.gui;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AISLinePicker implements ActionListener, ListSelectionListener {
	private JFrame linePickerFrame;
	public static final int CONNECTION_FRAME_SIZE_HEIGHT = 500;
	public static final int CONNECTION_FRAME_SIZE_WIDTH = 500;
	private JList list;
	private String datas[];
	private JButton submitButton;
	private String arffHeader = "";

	
	AISLinePicker(String path){
		linePickerFrame = new JFrame();
		
		linePickerFrame.setResizable(false);
		linePickerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		linePickerFrame.setTitle("Auswahl einer AIS-Zeile");
		linePickerFrame.setSize(CONNECTION_FRAME_SIZE_WIDTH, CONNECTION_FRAME_SIZE_HEIGHT);
		
		int screenSizeHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int screenSizeWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int cornerX = (screenSizeWidth / 2) - (CONNECTION_FRAME_SIZE_WIDTH / 2);
		int cornerY = (screenSizeHeight / 2) - (CONNECTION_FRAME_SIZE_HEIGHT / 2);
		linePickerFrame.setLocation(cornerX, cornerY);
		
		
		String aisData = null;
		try {
			aisData = new String(Files.readAllBytes(Paths.get(path)));
			// System.out.println(aisData);
		} catch (IOException e) {
		    TextLog.getInstance().write("Fehler: Datei konnte nicht geoeffnet werden! (" + path + ")");
			e.printStackTrace();
			return;
		}
		
		String lines[] = aisData.split("\\r?\\n");
		
		int lengthLines = lines.length;
		String data[] = new String[lengthLines];
		int numberValidLines = 0;
		for (int i=0; i<lengthLines; ++i){
			
			if(!lines[i].isEmpty()){
				
				char first = lines[i].charAt(0);
					if (first == '@'){
						arffHeader += lines[i];
						arffHeader += "\n";
					} else {
						if (first == '\n'){
							// skip linebreaks
						} else {
						
						data[numberValidLines] = lines[i];
						numberValidLines++;
						}
					}
			}
		}
		
		list = new JList(data); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(this);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JScrollPane listScroller = new JScrollPane(list);
		// listScroller.setPreferredSize(new Dimension(250, 80));
		
		submitButton = new JButton("Markierte Zeile auswaehlen");
		submitButton.setEnabled(false);
		submitButton.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(linePickerFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createParallelGroup()
					.addComponent(listScroller)
					.addGroup(groupLayout.createSequentialGroup()
							.addGap(162 - submitButton.getWidth() )
							.addComponent(submitButton)
							)
					
					)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(listScroller)
					.addComponent(submitButton)
					)
		);
		linePickerFrame.getContentPane().setLayout(groupLayout);
		
		linePickerFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
			handleInput();			
		
	}
	
	public void handleInput(){
			String selection = list.getSelectedValue().toString();
			String result = arffHeader + selection;
			System.out.println(result);
			
			
			try {
                (new Thread(new GUINetworkConnection(result))).start();
            }
            catch (Exception e) {
			    e.printStackTrace();
            }
			
			TextLog.getInstance().write("Es wurde die Zeile Nummer " + String.valueOf((list.getSelectedIndex() + 1)) + " ausgewaehlt.");
			
			linePickerFrame.setVisible(false);
			linePickerFrame.dispose();
			
	}
	
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {

	        if (list.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	        	submitButton.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	        	submitButton.setEnabled(true);
	            
	        }
	    }
	}
}
