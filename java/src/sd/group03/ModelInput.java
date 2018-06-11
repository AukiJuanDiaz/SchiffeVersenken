package sd.group03;

import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Utils;

import java.util.HashMap;
import java.util.Map;

public class ModelInput extends DenseInstance {

    private static HashMap<String,Integer> attributeIndexMap;

    static {
        attributeIndexMap = new HashMap<String, Integer>();
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

    public double value(String attrName) {
        // Only works if instance is attached to dataset...
        //return this.value(this.dataset().attribute(attrName));

        Integer index = getAttributeIndex(attrName);
        if(index == null) return Utils.missingValue();
        return value(index);
    }

    public static void setAttributeIndex(String s, Integer i) {
        attributeIndexMap.put(s, i);
    }

    public static Integer getAttributeIndex(String s) {
        return attributeIndexMap.get(s);
    }



    public void prettyPrint() {
        for(Map.Entry<String, Integer> entry : attributeIndexMap.entrySet()) {
            System.out.print(entry.getKey() + ": " + value(entry.getValue()) + "  ");
        }
        System.out.println("");
    }
}
