package sd.group03;

import org.json.JSONArray;
import org.json.JSONObject;

public class Route {

    private Agent[] agents;
    private String name;

    public Route(JSONObject obj) throws RuntimeException {

        name = obj.getString("name");

        System.out.println("Setting up Route: " + name);

        JSONArray jsonAgents = obj.getJSONArray("agents");

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
    }

    public double makePrediction(ModelInput inst) throws RuntimeException {
        ModelInput cpy = new ModelInput(inst);

        boolean skipping = true;

        for(int i = 0; i < agents.length; ++i) {

           if(agents[i].isApplicable(cpy)) {

              skipping = false;
              cpy = agents[i].makePrediction(cpy);
           }
           else if(!skipping) throw new RuntimeException("Agent rejected output from former Agent");
        }

        return cpy.value("RemainingTravelTimeInMinutes");
    }

    public boolean isApplicable(ModelInput inst) {

       for(Agent a : agents) {
           if(a.isApplicable(inst)) return true;
       }

       return false;
    }

}
