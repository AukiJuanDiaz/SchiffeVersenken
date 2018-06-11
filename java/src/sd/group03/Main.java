package sd.group03;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;

public class Main {

    public static void main(String[] args) {
    	

    	System.out.println("Hallo, Marrone");

        //String basePath = "H:\\git\\group03\\java";
        String basePath = "/home/thilo/Documents/Uni/sd/group03/java/";

        Path dataPath = Paths.get(basePath, "datasets/DataSetWithDateTimeFeatures.csv");
        Path configPath = Paths.get(basePath, "src/sampleConfig.json");

        try {

            DataSource src = new DataSource(dataPath.toString());
            Instances trainingSet = src.getDataSet();
            Instance inst = trainingSet.instance(2);

            Enumeration<Attribute> en = trainingSet.enumerateAttributes();
            while(en.hasMoreElements()) {
                Attribute att = en.nextElement();
                System.out.println("Att: " + att.name() + " index: " + att.index());
                ModelInput.setAttributeIndex(att.name(), att.index());
            }

            trainingSet.setClassIndex(ModelInput.getAttributeIndex("RemainingTravelTimeInMinutes"));

            Broker broker = new Broker(configPath.toString());

            ModelInput mi = broker.createModelInput(inst);

            mi.prettyPrint();

            Route r = broker.getRouteForPrediction(mi);
            double prediction = r.makePrediction(mi);

            System.out.println("Prediction: " + prediction);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
        
    }
}
