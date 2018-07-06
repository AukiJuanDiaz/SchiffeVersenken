package sd.group03.broker;

import weka.core.Utils;

import java.util.ArrayList;
import java.util.Iterator;

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

        Iterator<ModelInput> it = intermediateResults.iterator();
        
        // Skip starting point
        if(it.hasNext()) it.next();
        else return Utils.missingValue();

        double res = 0;
        while(it.hasNext()) res += it.next().value("RemainingTravelTimeInMinutes");
        return res;
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
