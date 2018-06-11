package sd.group03;

import org.json.JSONArray;
import org.json.JSONObject;
import weka.core.Instance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Broker {

    private Route[] routes;

    public Broker(String configPath) throws RuntimeException {

        try {

            String configFile = new String(Files.readAllBytes(Paths.get(configPath)));
            JSONObject jsonConfig = new JSONObject(configFile);

            JSONArray jsonRoutes = jsonConfig.getJSONArray("routes");

            routes = new Route[jsonRoutes.length()];

            for (int i = 0; i < jsonRoutes.length(); ++i) {
                Route r = new Route(jsonRoutes.getJSONObject(i));
                routes[i] = r;
            }
        }
        catch( IOException e){
            System.out.println(e.getMessage());
        }
    }

    public ModelInput createModelInput(Instance inst) {
        return new ModelInput(inst);
    }

    public Route getRouteForPrediction(ModelInput inst) {

        for(Route r : routes) {
            if(r.isApplicable(inst)) return r;
        }

        return null;
    }
}
