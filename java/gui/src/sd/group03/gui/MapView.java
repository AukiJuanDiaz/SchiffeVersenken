package sd.group03.gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapView extends JPanel{

	private static MapView instance;

    private BufferedImage image;
    private int route = -1;

    private double [] livexPoints = new double[4];
    private double [] liveyPoints = new double[4];
    
    private int drawRouteIdx = -1;

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

    public void otherMap() {
    	switch (route) {
			case 1:
			case 3:
			case 5:
			case 7:
				changeMap(2);
				break;
			case 2:
			case 4:
			case 6:
			case 8:
				changeMap(1);
				break;
		}	
    }

    public boolean isCorrectMap(String name) {
        int routeIndex = routeName2Index(name);

    	if(routeIndex == 1) {

    	    return route % 2 == 1;

    	}
    	else if (routeIndex == 2) {
    		
    		if (route == 0) {
    			return false;
    		}

    	    return route % 2 == 0;
    	}
    	return false;
    }
    
    
    //TODO
    public void changeMap_ifnec(String name) {
    	if (! isCorrectMap(name)) {
    		// HintergrundMap ändern
    		if (getMap() == 0) {
    			changeMap(routeName2Index(name));
    		}else {
	    		if(getMap() % 2 == 1) {
	    			// BremHH
	    			changeMap(getMap()+1);
	    		}else {
	    			// KielGd
	    			changeMap(getMap()-1);
	    		}
    		}
    	}
    }

    public void changeMap(String name) {
        changeMap(routeName2Index(name));
    }
    public void changeMap(int num) {

    	//if(route == num) return;

    	route = num;
    	String pic;
    	switch (route) {
    	   case 0:
    		   pic = "images/gross.png";
    		   MainFrame2.comboBox.setSelectedIndex(0);
    		   break;
		   case 1:
			   pic = "images/bh.png";			   
			   MainFrame2.checkBoxAgents.setSelected(false);
			   MainFrame2.checkBoxHistoric.setSelected(false);
			   MainFrame2.comboBox.setSelectedIndex(1);
			   break;
		   case 2:
			   pic = "images/kg.png";
			   MainFrame2.checkBoxAgents.setSelected(false);
			   MainFrame2.checkBoxHistoric.setSelected(false);
			   MainFrame2.comboBox.setSelectedIndex(2);
			   break;
		   case 3:
			   pic = "images/bhplot.png";
			   MainFrame2.checkBoxAgents.setSelected(false);
			   MainFrame2.checkBoxHistoric.setSelected(true);
			   MainFrame2.comboBox.setSelectedIndex(1);
			   break;
		   case 4:
			   pic = "images/kgplot.png";
			   MainFrame2.checkBoxAgents.setSelected(false);
			   MainFrame2.checkBoxHistoric.setSelected(true);
			   MainFrame2.comboBox.setSelectedIndex(2);
			   break;
		   case 5:
			   // bh ohne historic mit agent
			   pic = "images/bhagent.png";
			   MainFrame2.checkBoxAgents.setSelected(true);
			   MainFrame2.checkBoxHistoric.setSelected(false);
			   MainFrame2.comboBox.setSelectedIndex(1);
			   break;
		   case 6:
			   // kg ohne historic mit agent
			   pic = "images/kgagent.png";
			   drawlivePoints();
			   MainFrame2.checkBoxAgents.setSelected(true);
			   MainFrame2.checkBoxHistoric.setSelected(false);
			   MainFrame2.comboBox.setSelectedIndex(2);
			   break;
		   case 7:
			   // bh mit historic mit agent
			   pic = "images/bhplotagent.png";
			   MainFrame2.checkBoxAgents.setSelected(true);
			   MainFrame2.checkBoxHistoric.setSelected(true);
			   MainFrame2.comboBox.setSelectedIndex(1);
			   break;
		   case 8:
			   // kg mit historic mit agent
			   pic = "images/kgplotagent.png";
			   MainFrame2.checkBoxAgents.setSelected(true);
			   MainFrame2.checkBoxHistoric.setSelected(true);
			   MainFrame2.comboBox.setSelectedIndex(2);
			   break;
		   default:
			   pic = "images/gross.png";
			   break;
    	}

    	try {
    	    image = ImageIO.read(getClass().getResource(pic));
			updateUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void savePoints(JSONArray lons, JSONArray lats, String Routename) {
    	
    	drawRouteIdx = routeName2Index(Routename);
    	
    	for (int i = 0; i < 4;i++) {
    		livexPoints[i]=0;
    		liveyPoints[i]=0;
    	}
    	
    	for (int i = 0; i< lons.length(); i++) {
    		try {
				livexPoints[i] = lons.getDouble(i);
				liveyPoints[i] = lats.getDouble(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	
    }
    
    public void clearMap() {
    	changeMap(getMap());
    }

    public void resetPoints() {
    	for (int i = 0; i < 4;i++) {
    		livexPoints[i]=0;
    		liveyPoints[i]=0;
    	}
    	
    }
    
    public void drawlivePoints() {
    	
    	int area = 0;
    	if (route == 0) {
    		return;
    	}else if (route % 2 == 1 ) {
    		area = 1;
    	}else if (route == 2 || route == 4 || route == 6 || route == 8) {
    		area = 2;
    	}
    	
    	if (area == drawRouteIdx) {
	    	final Graphics2D graphics2D = image.createGraphics ();
	    	for (int i = 0; i < 4; i++) {
	    
	    		graphics2D.setColor ( Color.getHSBColor( 0.7f, 0f,  0f) );
	            graphics2D.fillRect (x_Geo2Pix(livexPoints[i],area)-3, y_Geo2Pix(liveyPoints[i],area)-11,6, 22);
	            graphics2D.fillRect (x_Geo2Pix(livexPoints[i],area)-11, y_Geo2Pix(liveyPoints[i],area)-3,22, 6);
	            
	    		graphics2D.setColor ( Color.getHSBColor( 0.7f, 0f,  1f) );
	            graphics2D.fillRect (x_Geo2Pix(livexPoints[i],area)-2, y_Geo2Pix(liveyPoints[i],area)-10,4, 20);
	            graphics2D.fillRect (x_Geo2Pix(livexPoints[i],area)-10, y_Geo2Pix(liveyPoints[i],area)-2,20, 4);
	    	}

	    	graphics2D.dispose ();

	    	updateUI();
    	}
   	}

	public int y_Geo2Pix(double lat, int route) {

		double TOP_LAT = 0;
		double BOT_LAT = 0;

		double height= (double)instance.getHeight();

		switch (route) {
		case 1: // Brhv-HH
//			TOP_LAT = 54.104;
//			BOT_LAT = 53.140;
			TOP_LAT = 55.07;
			BOT_LAT = 53.25;
			break;
		case 2: // Kiel-Gdynia
			TOP_LAT = 57.37;
			BOT_LAT = 53.04;
			break;
		default: // grosse Karte
			TOP_LAT = 0;
			BOT_LAT = 0;
			break;
		}

		int yPix = (int) (height - (lat - BOT_LAT)* height/(TOP_LAT - BOT_LAT) );
		return yPix;
	}

	public static int x_Geo2Pix(double lon, int route) {

		double LEFT_LON = 0;
		double RIGHT_LON = 0;

		double width= (double) instance.getWidth();

		switch (route) {
		case 1: // Brhv-HH
//			LEFT_LON = 7.973;
//			RIGHT_LON = 10.292;
			LEFT_LON = 6.83;
			RIGHT_LON = 11.1;
			break;
		case 2: // Kiel-Gdynia
//			LEFT_LON = 9.33;
//			RIGHT_LON = 20.26;
			LEFT_LON = 9.33;
			RIGHT_LON = 20.20;			

			break;
		default: // grosse Karte
			LEFT_LON = 0;
			RIGHT_LON = 0;
			break;
		}

		int xPix = (int) ((lon - LEFT_LON)*(width / (RIGHT_LON - LEFT_LON)));
		return xPix;
	}

    public int routeName2Index(String name) {
        if (name.contentEquals("Bremerhaven-Hamburg") || name.contentEquals("Bremerhaven-Hamburg Single Agent")) {
            return 1;
        }else if (name.contentEquals("Kiel-Gdynia")|| name.contentEquals("Kiel-Gdynia Single Agent")) {
            return 2;
        }
        return 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
       g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

}
