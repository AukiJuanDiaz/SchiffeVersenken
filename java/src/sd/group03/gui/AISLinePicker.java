package sd.group03.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class AISLinePicker implements ActionListener {
	private JFrame linePickerFrame;
	public static final int CONNECTION_FRAME_SIZE_HEIGHT = 500;
	public static final int CONNECTION_FRAME_SIZE_WIDTH = 500;
	private JList list;
	private String datas[];

	
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
			System.out.println(aisData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String lines[] = aisData.split("\\r?\\n");
		
		int lengthLines = lines.length;
		String data[] = new String[lengthLines];
		int numberValidLines = 0;
		for (int i=0; i<lengthLines; ++i){
			
			if(!lines[i].isEmpty()){
				
				char first = lines[i].charAt(0);
					if (first == '@'){
						// Do nothing
					} else {
						data[numberValidLines] = lines[i];
						numberValidLines++;
					}
			}
		}
		
		list = new JList(data); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		linePickerFrame.add(listScroller);
		
		linePickerFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
			handleInput();			
		
	}
	
	public void handleInput(){

	}
}
