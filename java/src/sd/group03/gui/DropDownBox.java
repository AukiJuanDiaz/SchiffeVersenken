package sd.group03.gui;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DropDownBox extends JComboBox implements ActionListener {
	
	DropDownBox(){
		 addItem("Gesamtes Gebiet");
		 addItem("Bremerhaven - Hamburg");
		 addItem("Kiel - Gdynia");
		 addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox ddb = (JComboBox) e.getSource();
		int i = ddb.getSelectedIndex();
		switch (i) {
		case 0:
			// Gesamtes Gebiet
			MainFrame2.mapSelected = 0;
			MainFrame2.checkBoxHistoric.setSelected(false);
			MainFrame2.checkBoxAgents.setSelected(false);
			MainFrame2.checkBoxHistoric.setEnabled(false);
			MainFrame2.checkBoxAgents.setEnabled(false);
			break;
		case 1:
			// Bremerhaven Gebiet
			if(MainFrame2.checkBoxHistoric.isSelected()){
				if(MainFrame2.checkBoxAgents.isSelected()){
					MainFrame2.mapSelected = 7;
				} else {
					MainFrame2.mapSelected = 3;
				}
			} else {
				if(MainFrame2.checkBoxAgents.isSelected()){
					MainFrame2.mapSelected = 5;
				} else {
					MainFrame2.mapSelected = 1;
				}
			}
			MainFrame2.checkBoxHistoric.setEnabled(true);
			MainFrame2.checkBoxAgents.setEnabled(true);
			break;
		case 2:
			// Kiel Gebiet
			if(MainFrame2.checkBoxHistoric.isSelected()){
				if(MainFrame2.checkBoxAgents.isSelected()){
					MainFrame2.mapSelected = 8;
				} else {
					MainFrame2.mapSelected = 4;
				}
			} else {
				if(MainFrame2.checkBoxAgents.isSelected()){
					MainFrame2.mapSelected = 6;
				} else {
					MainFrame2.mapSelected = 2;
				}
			}
			MainFrame2.checkBoxHistoric.setEnabled(true);
			MainFrame2.checkBoxAgents.setEnabled(true);
			break;
		default:
			break;
		}
        MapView.getInstance().changeMap(MainFrame2.mapSelected);
	}

}
