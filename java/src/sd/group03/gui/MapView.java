package sd.group03.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapView extends JPanel{

	private static MapView instance;

    private BufferedImage image;
    private int route = -1;

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

    		/*switch (route) {
    		case 1:
    		case 3:
    		case 5:
    		case 7:
    			return true;
    		default:
    			return false;
    		}*/
    	}
    	else if (routeIndex == 2) {

    	    return route % 2 == 0;

    		/*switch (route) {
    		case 2:
    		case 4:
    		case 6:
    		case 8:
    			return true;
    		default:
    			return false;
    		}*/
    	}
    	return false;
    }

    public void changeMap(String name) {
        changeMap(routeName2Index(name));
    }
    public void changeMap(int num) {

    	if(route == num) return;

    	route = num;
    	String pic;
    	switch (route) {
    	   case 0:
    		   pic = "images/gross.png";
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

    public void drawlivePoint(double xKord, double yKord) {

        int route = getMap();
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

    public int routeName2Index(String name) {
        if (name.contentEquals("Bremerhaven-Hamburg")) {
            return 1;
        }else if (name.contentEquals("Kiel-Gdynia")) {
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
