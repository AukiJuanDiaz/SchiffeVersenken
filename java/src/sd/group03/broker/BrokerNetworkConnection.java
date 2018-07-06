package sd.group03.broker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BrokerNetworkConnection implements Runnable {

    private Broker broker;
    private Socket socket;
    private BufferedWriter output;
    private BufferedReader input;

    private static ThreadLocal<BrokerNetworkConnection> currentConnection;

    static {
        currentConnection = new ThreadLocal<>();
    }

    public BrokerNetworkConnection(Broker b, Socket s) {

        broker = b;
        socket = s;
    }

    public void run() {

        currentConnection.set(this);

        try {
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Wait for json line
            JSONObject request = new JSONObject(input.readLine());

            System.out.println("Received JSON: " + request.toString());

            String rawData = request.getString("data");
            InputStream is = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.UTF_8));
            DataSource dataSource = new DataSource(is);
            Instances insts = dataSource.getDataSet();

            //PredictionResult pr = broker.makePrediction(insts.get(0));


            //Uncomment to evaluate models. Results in stdout.
            PredictionResult pr = null;
            broker.evaluateModels(insts);

            JSONObject result = createResultResponse(pr);

            guiPrintMessage(result);
            System.out.println(result);

            output.close();
            input.close();
            socket.close();
        }
        catch (Exception e) {
            guiPrintError(e.toString());
            e.printStackTrace();
        }
    }

    public JSONObject createResultResponse(PredictionResult pr) {

        JSONObject result = new JSONObject();
        try {
			result.put("type", "result");
		
	        if(pr != null) {
	        
	            result.put("ett", pr.getETT());
	            result.put("routeName", pr.getRouteName());
	            
	            JSONArray ett = new JSONArray();
	            JSONArray lat = new JSONArray();
	            JSONArray lon = new JSONArray();
	
	            for(ModelInput mi : pr.intermediateResults) {
	                //ett.put(mi.value("RemainingTravelTimeInMinutes"));
	                lat.put(mi.value("Latitude"));
	                lon.put(mi.value("Longitude"));
	            }
	
	            result.put("intermediateETT", ett);
	            result.put("intermediateLat", lat);
	            result.put("intermediateLon", lon);
	            
	        }
	        else {
	            result.put("type", "error");
	            result.put("message", "Broker could not make a prediction!");
	        }
        } catch (JSONException e) {
			e.printStackTrace();
		}
        return result;
    }

    public void sendString(String s) {
        try {
            output.write(s + "\n");
            output.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void guiPrintError(String s) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("type", "error");
            obj.put("message", s);
            guiPrintMessage(obj);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void guiPrintString(String s) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("type", "message");
            obj.put("message", s);
            guiPrintMessage(obj);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void guiPrintMessage(JSONObject obj) {

        BrokerNetworkConnection instance = currentConnection.get();

        if(instance != null) {
            instance.sendString(obj.toString());
        }
        else System.out.println("ThreadLocal BNC not set!");
    }
}
