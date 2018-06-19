package sd.group03;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawHistData {
	
	private static final double WIDTH = 774;
	private static final double HEIGHT = 543;
	
	// unterschiedlich von Bildausschnitt zu Bildausschnitt
	
	// WERTE BRHV-HH
	private static final double LEFT_LON = 7.973;
	private static final double RIGHT_LON = 10.292;
	
	private static final double TOP_LAT = 54.104;
	private static final double BOT_LAT = 53.140;

	public static int x_Geo2Pix(double lon) {
		
		int xPix = (int) ((lon - LEFT_LON)*(WIDTH / (RIGHT_LON - LEFT_LON)));
		return xPix;
	}

	public static int y_Geo2Pix(double lat) {
		int yPix = (int) (HEIGHT - (lat - BOT_LAT)* HEIGHT/(TOP_LAT - BOT_LAT) );
		return yPix;
	}
	

	public static void main(String[] args) throws IOException {
		
        final BufferedImage image = ImageIO.read(new File("src\\bh.jpg"));
        final Graphics2D graphics2D = image.createGraphics ();
        
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        

        try {
            br = new BufferedReader(new FileReader("../BrhvHH/Daten/TandTData/TrainingSet.csv"));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                
            	// use comma as separator
                String[] AisRow = line.split(cvsSplitBy);
                float lat = Float.parseFloat(AisRow[1]);
                float lon = Float.parseFloat(AisRow[2]);
                float rtt = Float.parseFloat(AisRow[0]);
                
               
                graphics2D.setColor ( Color.getHSBColor( rtt/1500, 1f,  0.5f) );
                graphics2D.fillRect (x_Geo2Pix(lon),y_Geo2Pix(lat),4,4 );

                  
                }
            System.out.println("fertig");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }




        graphics2D.dispose ();

        try {
			ImageIO.write ( image, "png", new File ( "image.png" ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
