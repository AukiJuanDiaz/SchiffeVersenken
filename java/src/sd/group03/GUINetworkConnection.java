package sd.group03;

import org.json.JSONObject;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GUINetworkConnection implements Runnable {
	
	private static String host = "127.0.0.1";
	private static short port = 9812;

    private Socket socket;
    private BufferedWriter output;
    private BufferedReader input;
    private String filePath;

    public GUINetworkConnection(String path) throws IOException {
        try {
            socket = new Socket(InetAddress.getByName(host), port);
        }
        catch (ConnectException e) {
            TextLog.getInstance().write("Could not connect to Broker at " + host + ":" + port);
        }
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        filePath = path;
    }
    
    public static void setHost(String newHost) {
    	host = newHost;	
    }
    
    public static void setPort (short newPort) {
    	port = newPort;
    }
    
    public static String getHost() {
    	return host;
    }
    
    public static short getPort() {
    	return port;
    }

    public void run() {

        try {
            JSONObject obj = new JSONObject();
            String aisData = new String(Files.readAllBytes(Paths.get(filePath)));

            obj.put("type", "prediction-request");
            obj.put("data", aisData);

            output.write(obj.toString() + "\n");
            output.flush();

            boolean finished = false;
            while (!finished) {
                String s = input.readLine();
                System.out.println("Received string: " + s);

                if(s == null || s.isEmpty()) {
                    System.out.println("Skipping...");
                    continue;
                }

                JSONObject response = new JSONObject(s);

                String type = response.getString("type");

                if ("message".equals(type)) {
                    String msg = response.getString("message");
                    TextLog.getInstance().write(msg);
                } else if ("result".equals(type)) {
                    double ett = response.getDouble("ett");
                    TextLog.getInstance().write("ETT: " + ett);
                    finished = true;
                }
                else if ("error".equals(type)) {
                    String msg = response.getString("message");
                    TextLog.getInstance().write("FEHLER!");
                    TextLog.getInstance().write(msg);
                    finished = true;
                }
            }

            output.close();
            input.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        TextLog.getInstance().write("Anfrage abgeschlossen");
    }
}
