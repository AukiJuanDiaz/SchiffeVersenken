package sd.group03;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DropDownBox extends JComboBox implements ActionListener {
	
	DropDownBox(){
		 this.addItem("Gesamtes Gebiet");
		 this.addItem("Bremerhaven - Hamburg");
		 this.addItem("Kiel - Gdynia");
		 
		 addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox ddb = (JComboBox) e.getSource();
        String route = (String)ddb.getSelectedItem();
        
        // Functionality for changing the map after using the drop down menu is implemented here.
        switch (route) { 
        case "Bremerhaven - Hamburg":  
        	MapView.getinstance().changeMap(1);
        	break;
        case "Kiel - Gdynia":  
        	MapView.getinstance().changeMap(2);
        	break;
        default: //"Gesamtes Gebiet"
        	MapView.getinstance().changeMap(0);
        	break;
        }
	}

}
