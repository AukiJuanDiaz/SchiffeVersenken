package sd.group03.broker;

import org.json.JSONArray;
import org.json.JSONObject;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;
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
            BrokerNetworkConnection.guiPrintString("Route gewaehlt: " + r.getName());
            PredictionResult result = r.makePrediction(mi);
            result.setRouteName(r.getName());

            String s = "Berechnung fertig!\nDas Schiff wird in " + result.getETT() + " minuten das Ziel erreichen.";
            BrokerNetworkConnection.guiPrintString(s);

            ModelInput last = result.getLast();
            s = "(Koordinaten: " + last.value("Latitude") + ", " + last.value("Longitude") + ")";
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
            actuals[i] = mi.value("RemainingTravelTimeInMinutes");
            predicteds[i] = makePrediction(inst).getETT();
        }

        System.out.println("Longs: " + Arrays.toString(longitudes));
        System.out.println("RTT: " + Arrays.toString(actuals));
        System.out.println("ETT: " + Arrays.toString(predicteds));
    }
}
