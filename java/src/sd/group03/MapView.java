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
	

	private static final double WIDTH = 774;
	private static final double HEIGHT = 543;
	

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
    
	public int y_Geo2Pix(double lat, int route) {

		double TOP_LAT = 0;
		double BOT_LAT = 0;

		
		switch (route) {
		case 1: // Brhv-HH
			TOP_LAT = 54.104;
			BOT_LAT = 53.140;
			break;
		case 2: // Kiel-Gdynia
			TOP_LAT = 0;
			BOT_LAT = 0;
			break;
		default: // grosse Karte
			TOP_LAT = 54.104;
			BOT_LAT = 53.140;
			break;
		}
		
		int yPix = (int) (HEIGHT - (lat - BOT_LAT)* HEIGHT/(TOP_LAT - BOT_LAT) );
		return yPix;
	}
	
	public static int x_Geo2Pix(double lon, int route) {
		
		double LEFT_LON = 7.973;
		double RIGHT_LON = 10.292;
				
		switch (route) {
		case 1: // Brhv-HH
			LEFT_LON = 7.973;
			RIGHT_LON = 10.292;
			break;
		case 2: // Kiel-Gdynia
			LEFT_LON = 0;
			RIGHT_LON = 0;
			break;
		default: // grosse Karte
			LEFT_LON = 0;
			RIGHT_LON = 0;
			break;
		}
		
		int xPix = (int) ((lon - LEFT_LON)*(WIDTH / (RIGHT_LON - LEFT_LON)));
		return xPix;
	}
 
    
    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
       g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}

