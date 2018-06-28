package sd.group03.broker;

import java.util.ArrayList;

public class PredictionResult {
	
	private String RouteName;

	public ArrayList<ModelInput> intermediateResults;
    public PredictionResult() {
        intermediateResults = new ArrayList<>();
    }
    
    public void setRouteName(String name) {
    	RouteName = name;
    }
    public String getRouteName() {
    	return RouteName;
    }

    public  void prettyPrint() {
        System.out.println(intermediateResults.size() + " intermediates with total ETT: " + getETT());
    }
    public double getETT() {
        ModelInput last = getLast();
        return last.value("RemainingTravelTimeInMinutes");
    }

    public ModelInput getLast() {
        return intermediateResults.get(intermediateResults.size()-1);
    }

    public boolean isValid() {
        return !intermediateResults.isEmpty();
    }

    public void addIntermediate(ModelInput mi) {
       intermediateResults.add(mi);
    }

}
