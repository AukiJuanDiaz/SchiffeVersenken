package sd.group03;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        Path configPath = Paths.get("src/sampleConfig.json");

        try {
            Broker broker = new Broker(configPath.toString());
            BrokerController bc = new BrokerController(broker);

            //PredictionResult prediction = bc.makePrediction(dataPath.toString());
            //System.out.println("Prediction: " + prediction.getETT());

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
        
    }
}
