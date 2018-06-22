package sd.group03;

import java.util.ArrayList;

public class PredictionResult {

    private ArrayList<ModelInput> intermediateResults;

    public PredictionResult() {
        intermediateResults = new ArrayList<>();
    }

    public  void prettyPrint() {
        System.out.println(intermediateResults.size() + " intermediates with total ETT: " + getETT());
    }
    public double getETT() {
        ModelInput last = intermediateResults.get(intermediateResults.size() -1);
        return last.value("RemainingTravelTimeInMinutes");
    }

    public boolean isValid() {
        return !intermediateResults.isEmpty();
    }

    public void addIntermediate(ModelInput mi) {
       intermediateResults.add(mi);
    }

}
