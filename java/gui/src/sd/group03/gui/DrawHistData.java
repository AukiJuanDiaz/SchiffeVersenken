package sd.group03.gui;

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
	
	//Werte Kiel-Gdynia
	private static double LEFT_LON = 0.0;
	private static double RIGHT_LON = 0.0;
	
	private static double TOP_LAT = 0.0;
	private static double BOT_LAT = 0.0;
	
	
	
	public static int x_Geo2Pix(double lon) {
		
		int xPix = (int) ((lon - LEFT_LON)*(WIDTH / (RIGHT_LON - LEFT_LON)));
		return xPix;
	}

	public static int y_Geo2Pix(double lat) {
		int yPix = (int) (HEIGHT - (lat - BOT_LAT)* HEIGHT/(TOP_LAT - BOT_LAT) );
		return yPix;
	}
	
	public static void drawbhhist()throws IOException {
		
		int max_bh = 1741;
		
        final BufferedImage image = ImageIO.read(new File("src/bh.png"));
        final Graphics2D graphics2D = image.createGraphics ();
        
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        

        try {
            br = new BufferedReader(new FileReader("../BrhvHH/Daten/TandTData/TrainingSet.csv"));
                        
            line = br.readLine(); // read the Header
            
            while ((line = br.readLine()) != null) {
                
            	// use comma as separator
                String[] AisRow = line.split(cvsSplitBy);
                float lat = Float.parseFloat(AisRow[1]);
                float lon = Float.parseFloat(AisRow[2]);
                float rtt = Float.parseFloat(AisRow[0]);
               
                graphics2D.setColor ( Color.getHSBColor( rtt/max_bh, 1f,  0.9f) );
                //graphics2D.drawLine(x_Geo2Pix(old_lon), y_Geo2Pix(old_lat), x_Geo2Pix(lon), y_Geo2Pix(lat));
                graphics2D.fillRect (x_Geo2Pix(lon),y_Geo2Pix(lat),2,3 );
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
			ImageIO.write ( image, "png", new File ("sd/group03/gui/images/bhplot.png") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	public static void drawkghist()throws IOException {
		
		int max_kg = 2996;
		
        final BufferedImage image = ImageIO.read(new File("src/kg.png"));
        final Graphics2D graphics2D = image.createGraphics ();
        
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        

        try {
            br = new BufferedReader(new FileReader("../KielGdynia/Daten/TandTData/TrainingSet.csv"));
            
            float old_lat = 0;
            float old_lon = 0;
            
            line = br.readLine(); // read the Header
            
            // read the first Koordinates
            if ((line = br.readLine()) != null) {
            	
            	String[] firstRow = line.split(cvsSplitBy);
                old_lat = Float.parseFloat(firstRow[1]);
                old_lon = Float.parseFloat(firstRow[2]);
            }
            
            while ((line = br.readLine()) != null) {
                
            	// use comma as separator
                String[] AisRow = line.split(cvsSplitBy);
                float lat = Float.parseFloat(AisRow[1]);
                float lon = Float.parseFloat(AisRow[2]);
                float rtt = Float.parseFloat(AisRow[0]);
                
                
                
                
               
                graphics2D.setColor ( Color.getHSBColor( rtt/max_kg, 1f,  0.9f) );
                //graphics2D.drawLine(x_Geo2Pix(old_lon), y_Geo2Pix(old_lat), x_Geo2Pix(lon), y_Geo2Pix(lat));
                graphics2D.fillRect (x_Geo2Pix(lon),y_Geo2Pix(lat),3,3 );
                
                old_lat = lat;
                old_lon = lon;


                  
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
			ImageIO.write ( image, "png", new File ( "bhkgplot.png" ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public static void drawAgent(double geo, String readfrom, String output)throws IOException {
		
        final BufferedImage image = ImageIO.read(new File(readfrom));
        final Graphics2D graphics2D = image.createGraphics ();
      
        
        
        graphics2D.setColor ( Color.getHSBColor( 0.7f, 1f,  0.9f) );
        graphics2D.fillRect (x_Geo2Pix(geo)-1,0,2, 774);
        System.out.println("fertig");

        graphics2D.dispose ();

        try {
			ImageIO.write ( image, "png", new File (output) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		
		int option = 2;
		
		switch (option) {
			case 0: // BH plot
			case 1: // BH Agent
			case 2: // BH Agentplot
				LEFT_LON = 6.83;
				RIGHT_LON = 11.1;
				TOP_LAT = 55.07;
				BOT_LAT = 53.25;
				
				break;
			case 3: //KG plot
			case 4: // KG Agent
			case 5: // KG Agent Plot
				//Werte Kiel-Gdynia
				LEFT_LON = 9.33;
				RIGHT_LON = 20.26;
				
				TOP_LAT = 57.37;
				BOT_LAT = 53.04;
				break;
		}
		
		//drawAgent(8.69,"src/bh.png","test.png");
		
		drawbhhist();
		
		
		

	}

}
