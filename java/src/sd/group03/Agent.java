package sd.group03;

import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import weka.core.Instance;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Agent {

    HashSet<Predictor> predictors;

    public Agent(String path) throws RuntimeException {
        System.out.println("\nLooking for models in path: " + path);

        predictors = new HashSet<Predictor>();
        for (Predictor.PredictorType pt : Predictor.PredictorType.values()) {

            String modelName = pt.name() + ".model";
            System.out.print("Looking for model: " + modelName + "...");
            Path predictorModel = Paths.get(path, modelName);

            if (Files.exists(predictorModel)) {
                System.out.println(" FOUND!");
                try {
                    Predictor p = new Predictor(pt, predictorModel.toString());
                    predictors.add(p);
                }
                catch(Exception e)  {
                   System.out.println(e.getMessage());
                }
            } else System.out.println("NOT FOUND!");
        }

        if (predictors.isEmpty()) {
            throw new RuntimeException("Found no appropriate model files in path: " + path);
        }
    }

    public Instance makePrediction(Instance inst) {
        Instance copy = (Instance) inst.copy();

        for (Predictor p : predictors) {
            double res = p.makePrediction(inst);
            copy.setValue(p.classIndex(), res);
        }
        return copy;
    }
}

