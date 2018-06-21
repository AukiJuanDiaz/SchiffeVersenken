package sd.group03;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class MapView extends JPanel{

	private static MapView instance;
	
    private BufferedImage image;
    private int route = 0; 

    public static MapView getInstance() {
	    if (instance == null) {
	    	instance = new MapView();
	    }
    	return instance;
    }
    
    private MapView() {
		changeMap(0);
    }

    public void changeMap(int num) {
    	route = num;
    	String pic;
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
			image = ImageIO.read(new File(Paths.get("src", pic).toString()));
			updateUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void drawlivePoint(int xKord, int yKord, int rgb) {
    	image.setRGB(xKord, yKord, rgb);
    	updateUI();
   	}
 
    
    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
       g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}

