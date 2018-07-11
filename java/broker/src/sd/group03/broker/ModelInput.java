package sd.group03.broker;

import weka.core.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ModelInput extends DenseInstance {

    private static List<String> attributes;

    static {
       attributes = Arrays.asList("RemainingTravelTimeInMinutes", "Latitude", "Longitude", "WeekdayInt", "WeekOfYearInt", "HourInt", "Length", "Breadth", "Draught", "SOG", "COG");
    }

    ModelInput() {
        super(attributes.size());
        setDataset(getNewDataset());
    }

    // In java werden konstruktoren nicht vererbt, daher rufen sie hier immer super auf
    ModelInput(double weight, double[] attValues) {
        super(weight, attValues);
    }

    ModelInput(Instance instance) {
        super(instance);

        // Weka normally discards the dataset when copying
        // this makes us unable to classify a copy
        // So we keep a reference to the original dataset
        m_Dataset = instance.dataset();
    }

    ModelInput(int numAttributes) {
        super(numAttributes);
    }

    public void setValue(String attrName, double val) {
        Integer index = getAttributeIndex(attrName);
        if(index != -1) setValue(index, val);
    }

    public double value(String attrName) {
        // Only works if instance is attached to dataset...
        //return this.value(this.dataset().attribute(attrName));

        Integer index = getAttributeIndex(attrName);
        if(index == -1) return Utils.missingValue();
        return value(index);
    }

    public static Integer getAttributeIndex(String s) {
        return attributes.indexOf(s);
    }

    public static Instances getNewDataset() {
        ArrayList<Attribute> atts = new ArrayList<>();

        for(String name : attributes) {
            atts.add(new Attribute(name));
        }

        return new Instances("dataset", atts,1);
    }

    public void setTimeFeatures(double wekaEpoch, int minuteOffset) {

        long seconds = (long) wekaEpoch / 1000;
        seconds += minuteOffset * 60;
        int nanoseconds = (int) (wekaEpoch % 1000) * 1000;

        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(seconds, nanoseconds, ZoneOffset.ofHours(1));

        setValue("WeekdayInt", dateTime.getDayOfWeek().getValue());
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        setValue("WeekOfYearInt", dateTime.get(weekFields.weekOfWeekBasedYear()));
        setValue("HourInt", dateTime.getHour());
    }

    public void setTimeFeatures(double wekaEpoch) {
        setTimeFeatures(wekaEpoch, 0);
    }

    void prettyPrint() {
        for(String a : attributes) {
            System.out.println(a + ": " + value(a));
        }
        System.out.print("");
    }
}
