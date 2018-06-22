package sd.group03;
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
        MapView.getInstance().changeMap(ddb.getSelectedIndex());
	}

}
