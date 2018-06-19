package sd.group03;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

import java.io.IOException;
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

            String configFile = new String(Files.readAllBytes(Paths.get(configPath)));
            JSONObject jsonConfig = new JSONObject(configFile);

            JSONArray jsonRoutes = jsonConfig.getJSONArray("routes");

            routes = new Route[jsonRoutes.length()];

            for (int i = 0; i < jsonRoutes.length(); ++i) {
                Route r = new Route(jsonRoutes.getJSONObject(i));
                routes[i] = r;
            }
        }
        catch( IOException e) {
            System.out.println(e.getMessage());
        }
        catch (JSONException e) {
			// TODO Auto-generated catch block
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

        if(mi.value("WeekdayInt") == Utils.missingValue()) {
            double wekaEpoch = inst.value(dataset.attribute("time"));
            long seconds = (long) wekaEpoch / 1000;
            int nanoseconds = (int) (wekaEpoch % 1000) * 1000;

            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(seconds, nanoseconds, ZoneOffset.ofHours(1));

            mi.setValue("WeekDayInt", dateTime.getDayOfWeek().getValue());
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

    public PredictionResult makePrediction(Instance inst) {
        ModelInput mi = createModelInput(inst);
        mi.prettyPrint();
        return getRouteForPrediction(mi).makePrediction(mi);
    }
}
