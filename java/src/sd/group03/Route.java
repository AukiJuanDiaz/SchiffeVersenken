package sd.group03;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Route {

    private Agent[] agents;
    private String name;

    public Route(JSONObject obj) throws RuntimeException {

        try {
			name = obj.getString("name");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        System.out.println("Setting up Route: " + name);

        JSONArray jsonAgents;
		try {
			jsonAgents = obj.getJSONArray("agents");


        agents = new Agent[jsonAgents.length()];

	        for(int i = 0; i < jsonAgents.length(); ++i) {
	
	            try {
	                Agent a = new Agent((JSONObject) jsonAgents.get(i));
	                agents[i] = a;
	            }
	            catch (RuntimeException e) {
	               System.out.println("Could not create Agent from json data: " + (JSONObject) jsonAgents.get(i));
	            }
	        }
        
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    public PredictionResult makePrediction(ModelInput inst) throws RuntimeException {

        PredictionResult pr = new PredictionResult();

        ModelInput cpy = new ModelInput(inst);

        boolean skipping = true;

        for(Agent a : agents) {

            if(a.isApplicable(cpy)) {

                skipping = false;
                cpy = a.makePrediction(cpy);
                pr.addIntermediate(new ModelInput(cpy));
            }
            else if(!skipping) throw new RuntimeException("Agent rejected output from former Agent");
        }
        return pr;
    }

    public boolean isApplicable(ModelInput inst) {

       for(Agent a : agents) {
           if(a.isApplicable(inst)) return true;
       }

       return false;
    }

}
