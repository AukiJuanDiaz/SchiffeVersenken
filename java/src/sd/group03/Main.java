package sd.group03;

import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import weka.core.converters.ConverterUtils.DataSource;

public class Main {

    public static void main(String[] args) {
    	

    	System.out.println("Hallo, Marrone");
    	System.out.println("Hallo, Team");
    	
        String basePath = "H:\\git\\group03\\java";
        Path agentPath = Paths.get(basePath, "agents/test");
        Path dataPath = Paths.get(basePath, "datasets/Data_prepared_complete.arff");

        try {

            DataSource src = new DataSource(dataPath.toString());
            Instances trainingSet = src.getDataSet();

            trainingSet.deleteAttributeAt(0);
            trainingSet.deleteAttributeAt(0);
            trainingSet.deleteAttributeAt(0);
            trainingSet.deleteAttributeAt(0);

            trainingSet.deleteAttributeAt(1);
            trainingSet.deleteAttributeAt(1);


            trainingSet.deleteAttributeAt(2);
            trainingSet.deleteAttributeAt(2);
            trainingSet.deleteAttributeAt(2);

            trainingSet.deleteAttributeAt(3);
            trainingSet.deleteAttributeAt(trainingSet.numAttributes()-1);
            //trainingSet.setClassIndex(3);


            //Instance inst = trainingSet.firstInstance();
            Instance inst = trainingSet.instance(2);
            //System.out.println("Class " + inst.classAttribute() + " " + inst.classValue());

            Enumeration<Attribute> en = inst.enumerateAttributes();

            while(en.hasMoreElements())
            {
                Attribute a = en.nextElement();
                //System.out.println("Att: " + a);
            }
            //inst.setClassMissing();

            Agent agent = new Agent(agentPath.toString());

            System.out.println(inst);
            System.out.println(agent.makePrediction(inst));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }
}
