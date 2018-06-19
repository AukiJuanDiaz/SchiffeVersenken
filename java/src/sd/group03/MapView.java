package sd.group03;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;

public class MapView extends JPanel{

	private static MapView instance;
	
    private BufferedImage image;
    private int route = 0; 

    public static MapView getinstance() {
	    if (MapView.instance == null) {
	    	MapView.instance = new MapView();
	    }
    	return instance;
    }
    
    private MapView() {
       try {
    	   image = ImageIO.read(new File("src\\gross.jpg"));
      
       } catch (IOException ex) {
            System.out.println("error opeining the background image");
       }
    }

    public void changeMap(int num) {
    	route = num;
    	String pic = "";
    	switch (route) {
		   case 1:
			   pic = "bh.jpg";
			   break;
		   case 2:
			   pic = "kg.jpg";
			   break;
		   default:
			   pic = "gross.jpg";
			   break;
   
    	}
    	try {
			image = ImageIO.read(new File("src\\"+ pic));
			instance.updateUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void drawlivePoint(int xKord, int yKord, int rgb) {
    	instance.image.setRGB(xKord, yKord, rgb);
    	instance.updateUI();
   	}
 
    
    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
       g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}

