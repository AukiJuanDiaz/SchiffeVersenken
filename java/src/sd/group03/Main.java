package sd.group03;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        //String basePath = "H:\\git\\group03\\java";
        String basePath = "/home/thilo/Documents/Uni/sd/group03/java/";

        Path dataPath = Paths.get(basePath, "datasets/DataSetWithDateTimeFeatures.csv");
        Path configPath = Paths.get(basePath, "src/sampleConfig.json");

        try {

            Broker broker = new Broker(configPath.toString());
            BrokerController bc = new BrokerController(broker);

            //PredictionResult prediction = bc.makePrediction(dataPath.toString());
            //System.out.println("Prediction: " + prediction.getETT());

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        MainFrame2 window = new MainFrame2(bc);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
        
    }
}
