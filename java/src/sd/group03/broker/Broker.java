package sd.group03.broker;

import org.json.JSONArray;
import org.json.JSONObject;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Scanner;

public class Broker {

    private Route[] routes;

    public Broker(String configPath) throws RuntimeException {

        try {
            //System.out.println("Trying to load config: " + config);

            Scanner s = new Scanner(getClass().getResourceAsStream(configPath)).useDelimiter("\\A");
            String configFile = s.hasNext() ? s.next() : "";

            //String configFile = new String(Files.readAllBytes(Paths.get(config.getFile())));
            JSONObject jsonConfig = new JSONObject(configFile);

            JSONArray jsonRoutes = jsonConfig.getJSONArray("routes");

            routes = new Route[jsonRoutes.length()];

            for (int i = 0; i < jsonRoutes.length(); ++i) {
                Route r = new Route(jsonRoutes.getJSONObject(i));
                routes[i] = r;
            }
        }
        catch(Exception e) {
			e.printStackTrace();
		}
    }

    private ModelInput createModelInput(Instance inst) {
        Instances dataset = inst.dataset();

        ModelInput mi = new ModelInput();

        Enumeration<Attribute> en = dataset.enumerateAttributes();

        while(en.hasMoreElements()) {
            Attribute a = en.nextElement();
            mi.setValue(a.name(), inst.value(a));
        }

        double wekaEpoch = inst.value(dataset.attribute("time"));
        mi.setTimeFeatures(wekaEpoch);

        return mi;
    }

    private Route getRouteForPrediction(ModelInput inst) {

        for(Route r : routes) {
            if(r.isApplicable(inst)) return r;
        }

        return null;
    }

    // Habe ich mal synchronized gemacht um etwaige probleme mit Weka und threads zu vermeiden
    // Kann aber auch sein, dass es ohne funktioniert
    synchronized public PredictionResult makePrediction(Instance inst) throws RuntimeException {
        ModelInput mi = createModelInput(inst);
        mi.prettyPrint();
        Route r = getRouteForPrediction(mi);

        if(r != null) {
            BrokerNetworkConnection.guiPrintString("Route gewaehlt: " + r.getName());

            double startTime = inst.value(inst.dataset().attribute("time"));
            PredictionResult result = r.makePrediction(mi, startTime);
            result.setRouteName(r.getName());
            
            
            String s = "Berechnung fertig!\nDas Schiff wird in " + ((int) result.getETT()) + " Minuten das Ziel erreichen.";
            BrokerNetworkConnection.guiPrintString(s);

            ModelInput last = result.getLast();
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            s = "(Koordinaten: " + numberFormat.format(last.value("Latitude")) + ", " + numberFormat.format(last.value("Longitude")) + ")";
            BrokerNetworkConnection.guiPrintString(s);

            return result;
        }
        else return null;
    }

    public void evaluateModels(Instances dataset) {

        int size = dataset.size();

        double[] longitudes = new double[size];
        double[] actuals = new double[size];
        double[] predicteds = new double[size];

        for(int i = 0; i < size; ++i) {
            Instance inst = dataset.get(i);
            ModelInput mi = createModelInput(inst);

            longitudes[i] = mi.value("Longitude");

            //Fall AIS-Data-Format
            double end = inst.value(7);
            double now = inst.value(11);
            actuals[i] = (end-now)/60000;

            // Unbenutzt glaube ich
            //actuals[i] = mi.value("RemainingTravelTimeInMinutes");

            // Fall TAndT-Data-Format
            // actuals[i] = inst.value(inst.numValues()-1);

            PredictionResult pr = makePrediction(inst);
            predicteds[i] = pr.getETT();
        }

        System.out.println("lon = " + Arrays.toString(longitudes) + ";");
        System.out.println("rtt = " + Arrays.toString(actuals) + ";");
        System.out.println("ett = " + Arrays.toString(predicteds) + ";");
    }
}
