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
    
    public int getMap() {
    	return route;
    }

    public void changeMap(int num) {
    	route = num;
    	String pic = "";
    	switch (route) {
    	   case 0:
    		   pic = "gross.png";
    		   break;
		   case 1:
			   pic = "bh.png";
			   break;
		   case 2:
			   pic = "kg.png";
			   break;
		   case 3:
			   pic = "bhplot.png";
			   break;
		   case 4:
			   pic = "kgplot.png";
			   break;
		   case 5:
			   // bh ohne historic mit agent
			   break;
		   case 6:
			   // kg ohne historic mit agent
			   break;
		   case 7:
			   // bh mit historic mit agent
			   break;
		   case 8:
			   // kg mit historic mit agent
			   break;
		   default:
			   pic = "gross.png";
			   break;
    	}
    	try {
			image = ImageIO.read(new File(Paths.get("src", pic).toString()));
			updateUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void drawlivePoint(double xKord, double yKord, int route) {
      
    	final Graphics2D graphics2D = image.createGraphics ();
        graphics2D.setColor ( Color.getHSBColor( 0.7f, 1f,  0.9f) );
        graphics2D.fillRect (x_Geo2Pix(xKord,route), y_Geo2Pix(yKord,route),10, 10);
        
        graphics2D.dispose ();

    	updateUI();
   	}
    
	public int y_Geo2Pix(double lat, int route) {

		double TOP_LAT = 0;
		double BOT_LAT = 0;
		
		double height= (double)instance.getHeight();


		
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
		
		int yPix = (int) (height - (lat - BOT_LAT)* height/(TOP_LAT - BOT_LAT) );
		return yPix;
	}
	
	public static int x_Geo2Pix(double lon, int route) {
		
		double width= (double) instance.getWidth();
		
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
		
		int xPix = (int) ((lon - LEFT_LON)*(width / (RIGHT_LON - LEFT_LON)));
		return xPix;
	}
 
    
    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
       g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}

