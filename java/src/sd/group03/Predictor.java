package sd.group03;

import org.json.JSONObject;
import weka.classifiers.Classifier;
import weka.core.Instance;

public class Predictor {

    private Classifier model;
    private String classAttribute;

    public Predictor(JSONObject obj) throws Exception
    {
        try {
            String modelPath = obj.getString("modelPath");
            model = (Classifier) weka.core.SerializationHelper.read(modelPath);
            classAttribute = obj.getString("classAttribute");
        }
        catch (Exception e)
        {
            System.out.println("Error reading model file: " );
            throw e;
        }
    }

    public double makePrediction(Instance inst)
    {
        return 0;
        /*double res = Utils.missingValue();

        try {
            // Set and reset the class attribute to the appropriate value for this predictor
            int oldClassIndex = inst.classIndex();
            inst.dataset().setClassIndex(getClassIndex());
            res = model.classifyInstance(inst);
            inst.dataset().setClassIndex(oldClassIndex);
        }
        catch (Exception e)
        {
            System.out.println("Could not predict instance: " + e);
            e.printStackTrace();
        }
        return res;*/
    }

    // Calculate RMSE for instances
    // DOES NOT WORK FOR COG -> 360 and 0 degrees are the same and not maximally different
    public double testModel(Instance[] instances)
    {
        double error = 0;

        for(Instance i : instances)
        {
            double actual = i.value(getClassIndex());
            double predicted = makePrediction(i);

            System.out.println("Error: " + (actual - predicted));

            error += Math.pow(actual - predicted, 2);
        }

        error /= instances.length;

        return Math.sqrt(error);
    }

    public String getClassAttribute() {
        return classAttribute;
    }

    public int getClassIndex() {
        return ModelInput.getAttributeIndex(getClassAttribute());
    }
}
