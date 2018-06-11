package sd.group03;

import org.json.JSONObject;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Utils;

public class Predictor {

    public enum PredictorType
    {
        PT_COMPLETE,
        PT_SOG
    }

    private Classifier model;
    public PredictorType type;
    private int classIndex;

    public Predictor(JSONObject obj) throws Exception
    {
        try {
            type = PredictorType.valueOf(obj.getString("predictorType"));
            String modelPath = obj.getString("modelPath");
            model = (Classifier) weka.core.SerializationHelper.read(modelPath);
            classIndex = obj.getInt("classIndex");
        }
        catch (Exception e)
        {
            System.out.println("Error reading model file: " );
            throw e;
        }
    }

    public double makePrediction(Instance inst)
    {
        double res = Utils.missingValue();

        try {
            res = model.classifyInstance(inst);
            //System.out.println("Prediction: " + res);
        }
        catch (Exception e)
        {
            System.out.println("Could not predict instance: " + e.getMessage());
        }
        return res;
    }

    // Calculate RMSE for instances
    // DOES NOT WORK FOR COG -> 360 and 0 degrees are the same and not maximally different
    public double testModel(Instance[] instances)
    {
        double error = 0;

        for(Instance i : instances)
        {
            double actual = i.value(classIndex);
            double predicted = makePrediction(i);

            System.out.println("Error: " + (actual - predicted));

            error += Math.pow(actual - predicted, 2);
        }

        error /= instances.length;

        return Math.sqrt(error);
    }

    public int getClassIndex() {
        return classIndex;
    }
}
