package sd.group03;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class BrokerController {

    private Broker model;

    public BrokerController(Broker m) {
        model = m;
    }

    public PredictionResult makePrediction(String filePath) {

        try {
            DataSource src = new DataSource(filePath);
            Instances trainingSet = src.getDataSet();
            Instance inst = trainingSet.instance(0);
            PredictionResult pr = model.makePrediction(inst);
            TextLog.getInstance().write("Prediction: " + pr.getETT());
        }
        catch (Exception e) {
           System.out.println("Error during prediction: " + e.getMessage());
        }
        return null;
    }
}
