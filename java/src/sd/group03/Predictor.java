package sd.group03;

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

    public Predictor(PredictorType t, String modelPath) throws Exception
    {
        type = t;
        try {
            model = (Classifier) weka.core.SerializationHelper.read(modelPath);
        }
        catch (Exception e)
        {
            System.out.println("Error reading model file: " + modelPath);
            throw e;
        }
    }

    public double makePrediction(Instance inst)
    {
        double res = Utils.missingValue();

        try {
            res = model.classifyInstance(inst);
            System.out.println("Prediction: " + res);
        }
        catch (Exception e)
        {
            System.out.println("Could not predict instance: " + e.getMessage());
        }
        return res;
    }

    public int classIndex()
    {
        return classIndexForPredictorType(this.type);
    }


    // Calculate RMSE for instances
    // DOES NOT WORK FOR COG -> 360 and 0 degrees are the same and not maximally different
    public double testModel(Instance[] instances)
    {
        double error = 0;

        for(Instance i : instances)
        {
            double actual = i.value(classIndex());
            double predicted = makePrediction(i);

            error += Math.pow(actual - predicted, 2);
        }

        error /= instances.length;

        return Math.sqrt(error);
    }

    // Index for the attribute that will be predicted.
    // MUST BE ADJUSTED!!
    static private int classIndexForPredictorType(PredictorType t)
    {
        switch (t)
        {
            case PT_COMPLETE:
                return 3;
            case PT_SOG:
                return 4;
            default:
                return -1;
        }
    }
}
