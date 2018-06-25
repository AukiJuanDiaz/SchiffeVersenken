package sd.group03;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.WeekFields;
import java.util.Enumeration;
import java.util.Locale;

public class Broker {

    private Route[] routes;

    public Broker(String configPath) throws RuntimeException {

        try {

            System.out.println("Trying to load config: " + Paths.get(configPath).toString());

            String configFile = new String(Files.readAllBytes(Paths.get(configPath)));
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

        // TODO: fix, ist leider buggy bei arff input
        //if(mi.value("WeekdayInt") == Utils.missingValue())
        {
            double wekaEpoch = inst.value(dataset.attribute("time"));
            long seconds = (long) wekaEpoch / 1000;
            int nanoseconds = (int) (wekaEpoch % 1000) * 1000;

            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(seconds, nanoseconds, ZoneOffset.ofHours(1));

            mi.setValue("WeekdayInt", dateTime.getDayOfWeek().getValue());
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            mi.setValue("WeekOfYearInt", dateTime.get(weekFields.weekOfWeekBasedYear()));
            mi.setValue("HourInt", dateTime.getHour());

        }

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
            try {
				BrokerNetworkConnection.guiPrintString("Route gewaehlt: " + r.getName());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            PredictionResult result = r.makePrediction(mi);

            String s = "Berechnung fertig!\nDas Schiff wird in " + result.getETT() + " minuten das Ziel erreichen.";
            try {
				BrokerNetworkConnection.guiPrintString(s);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            ModelInput last = result.getLast();
            s = "(Koordinaten: " + last.value("Latitude") + ", " + last.value("Longitude") + ")";
            try {
				BrokerNetworkConnection.guiPrintString(s);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            return result;
        }
        else return null;
    }
}
