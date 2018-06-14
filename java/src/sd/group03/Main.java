package sd.group03;

import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import weka.core.converters.ConverterUtils.DataSource;

public class Main {

    public static void main(String[] args) {
    	

    	System.out.println("Hallo, Marrone");

        //String basePath = "H:\\git\\group03\\java";
        String basePath = "/home/thilo/Documents/Uni/sd/group03/java/";

        Path dataPath = Paths.get(basePath, "datasets/DataSetWithDateTimeFeatures.csv");
        Path configPath = Paths.get(basePath, "src/sampleConfig.json");

        try {
            Broker broker = new Broker(configPath.toString());
            BrokerController bc = new BrokerController(broker);

            PredictionResult prediction = bc.makePrediction(dataPath.toString());

            System.out.println("Prediction: " + prediction.getETT());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
        
    }
}
