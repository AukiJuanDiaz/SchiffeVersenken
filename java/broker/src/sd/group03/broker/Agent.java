package sd.group03.broker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weka.core.Utils;

public class Agent {

    private Predictor[] predictors;
    private double minimumLongitude, maximumLongitude;
    private double showLongitude, showLatitude;

    public Agent(JSONObject obj) throws RuntimeException {

        try {
			minimumLongitude = obj.getDouble("minLong");
	        maximumLongitude = obj.getDouble("maxLong");
	        showLongitude = obj.optDouble("showLong", maximumLongitude);
	        showLatitude = obj.optDouble("showLat", Utils.missingValue());
	
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

        BrokerNetworkConnection.guiPrintString("Agent mit Breitengrad [" + minimumLongitude + ", " +
                                                maximumLongitude + ") trifft Vorhersage...");

        for (Predictor p : predictors) {
            double res = p.makePrediction(inst);
            copy.setValue(p.getClassIndex(), res);
        }

        copy.setValue("Longitude", showLongitude);

        if(!Double.isNaN(showLatitude)) {
           copy.setValue("Latitude", showLatitude);
        }

        return copy;
    }

    public boolean isApplicable(ModelInput inst) {

        double instLong = inst.value("Longitude");

        //inst.prettyPrint();

        return ( minimumLongitude <= instLong && instLong < maximumLongitude );
    }
}

