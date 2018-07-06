package sd.group03.broker;

import org.json.JSONObject;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Utils;

public class Predictor {

    private Classifier model;
    private String classAttribute;

    public Predictor(JSONObject obj) throws Exception
    {
        try {
            String modelPath = obj.getString("modelPath");
            //model = (Classifier) weka.core.SerializationHelper.read(modelPath);
            model = (Classifier) weka.core.SerializationHelper.read(getClass().getResourceAsStream(modelPath));
            classAttribute = obj.getString("classAttribute");
        }
        catch (Exception e)
        {
            System.out.println("Error reading model file: " + e.getMessage());
            throw e;
        }
    }

    public double makePrediction(Instance inst) throws RuntimeException
    {
        BrokerNetworkConnection.guiPrintString("Treffe Vorhersage fuer: " + getClassAttribute());
        //return 0;
        //throw new RuntimeException("Test error");
        double res = Utils.missingValue();

        try {
            // Set and reset the class attribute to the appropriate value for this predictor
            //int oldClassIndex = inst.classIndex();
            //inst.dataset().setClassIndex(getClassIndex());
            inst.dataset().setClassIndex(0);
            res = model.classifyInstance(inst);
            //inst.dataset().setClassIndex(oldClassIndex);
        }
        catch (Exception e)
        {
            System.out.println("Could not predict instance: " + e);
            e.printStackTrace();
        }
        return res;
    }

    public String getClassAttribute() {
        return classAttribute;
    }

    public int getClassIndex() {
        return ModelInput.getAttributeIndex(getClassAttribute());
    }
}
