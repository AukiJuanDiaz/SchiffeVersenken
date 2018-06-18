package sd.group03;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapView extends JPanel{

    private BufferedImage image;

    public MapView() {
       try {                
          image = ImageIO.read(new File("H:\\git\\group03\\java\\src\\sd\\group03\\BackgroundDefaultMap.png"));
       } catch (IOException ex) {
            System.out.println("error opeining the background image");
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}

