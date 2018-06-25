package sd.group03;

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

            PredictionResult pr = broker.makePrediction(insts.get(0));

            JSONObject result = new JSONObject();
            result.put("type", "result");

            if(pr != null) result.put("ett", pr.getETT());
            else result.put("ett", -1);

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
        JSONObject obj = new JSONObject();
        obj.put("type", "error");
        obj.put("message", s);
        guiPrintMessage(obj);
    }

    public static void guiPrintString(String s) {
            JSONObject obj = new JSONObject();
            obj.put("type", "message");
            obj.put("message", s);
            guiPrintMessage(obj);
    }

    public static void guiPrintMessage(JSONObject obj) {

        BrokerNetworkConnection instance = currentConnection.get();

        if(instance != null) {
            instance.sendString(obj.toString());
        }
        else System.out.println("ThreadLocal BNC not set!");
    }
}
