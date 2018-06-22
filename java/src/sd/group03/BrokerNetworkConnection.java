package sd.group03;

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

    public BrokerNetworkConnection(Broker b, Socket s) {
        broker = b;
        socket = s;
    }

    public void run() {

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

            PredictionResult pr = broker.makePrediction(insts.get(0));

            JSONObject result = new JSONObject();
            result.put("type", "result");

            if(pr != null) result.put("ett", pr.getETT());
            else result.put("ett", -1);

            guiPrintMessage(result);
            System.out.println(result);

            socket.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return;
        }
    }

    public void guiPrintString(String s) {
        JSONObject obj = new JSONObject();
        try {
			obj.put("type", "message");
	        obj.put("message", s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        guiPrintMessage(obj);
    }

    public void guiPrintMessage(JSONObject obj) {
        try {
            output.write(obj.toString());
            output.flush();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
