package sd.group03;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Agent {

    private Predictor[] predictors;
    private double minimumLongitude, maximumLongitude;

    public Agent(JSONObject obj) throws RuntimeException {

        try {
			minimumLongitude = obj.getDouble("minLong");
	        maximumLongitude = obj.getDouble("maxLong");
	
	        JSONArray jsonPredictors = obj.getJSONArray("predictors");
	
	        predictors = new Predictor[jsonPredictors.length()];
	
	        for(int i = 0; i < jsonPredictors.length(); ++i) {
	            try {
	                Predictor p = new Predictor( (JSONObject) jsonPredictors.get(i));
	                predictors[i] = p;
	            }
	            catch (Exception e) {
	                e.printStackTrace();
	                throw new RuntimeException("Error initializing Predictor!");
	            }
	        }
		} catch (JSONException e) {
			e.printStackTrace();
            throw new RuntimeException("Invalid config file!");
		}
    }

    public ModelInput makePrediction(ModelInput inst) throws RuntimeException {
        ModelInput copy = new ModelInput(inst);

        for (Predictor p : predictors) {
            double res = p.makePrediction(inst);
            copy.setValue(p.getClassIndex(), res);
        }
        return copy;
    }

    public boolean isApplicable(ModelInput inst) {

        double instLong = inst.value("Longitude");

        //inst.prettyPrint();

        return ( minimumLongitude <= instLong && instLong < maximumLongitude );
    }
}

