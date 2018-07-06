package sd.group03.gui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GUINetworkConnection implements Runnable {
	
	private static String host = "127.0.0.1";
	private static short port = 9812;
	private static int readTimeout = 10000;

    private Socket socket;
    private BufferedWriter output;
    private BufferedReader input;
    // private String filePath;
    private String AISfile;

    public GUINetworkConnection(String AIS) throws IOException, SocketException {
        try {
            socket = new Socket(InetAddress.getByName(host), port);
            socket.setSoTimeout(readTimeout);
        }
        catch (ConnectException e) {
            TextLog.getInstance().write("FEHLER: Es konnte keine Verbindung zum Broker hergestellt werden bei " + host + ":" + port);
            throw e;
        }
        catch (SocketException e) {
            TextLog.getInstance().write("FEHLER: Es konnte kein Socket-Timeout gesetzt werden.");
            throw e;
        }
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        AISfile = AIS;
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
    

    private boolean parseBrokerResponse(String resp) {

        System.out.println("Empfangene Nachricht: " + resp);

        if(resp == null || resp.isEmpty()) {
            System.out.println("Überspringe leere Antworten...");
            return false;
        }

        try {

            JSONObject response = new JSONObject(resp);
            String type = response.getString("type");

            switch (type) {
                case "message":
                    parseBrokerMessage(response);
                    return false;
                case "error":
                    parseBrokerError(response);
                    return true;
                case "result":
                    parseBrokerResult(response);
                    return true;
                default:
                    TextLog.getInstance().write("FEHLER: Antwort des Brokers konnte nicht verarbeitet werden!");
                    TextLog.getInstance().write(resp);
                    return true;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void parseBrokerError(JSONObject response) throws JSONException {
        String msg = response.getString("message");
        TextLog.getInstance().write("FEHLER!");
        TextLog.getInstance().write(msg);
    }

    private void parseBrokerMessage(JSONObject response) throws JSONException {
        String msg = response.getString("message");
        TextLog.getInstance().write(msg);
    }

    private void parseBrokerResult(JSONObject response) {

        try {
            //double ett = response.getDouble("ett");
            String route = response.getString("routeName");
            JSONArray intLat = response.getJSONArray("intermediateLat");
            JSONArray intLon = response.getJSONArray("intermediateLon");

            MapView.getInstance().changeMap_ifnec(route);
            for (int i = 0; i < intLat.length(); i++) {
                MapView.getInstance().savePoints(intLon, intLat, route);
            }
            MapView.getInstance().drawlivePoints();
            
        }
        catch (JSONException e) {
            e.printStackTrace();
            TextLog.getInstance().write("FEHLER: Falschformatierte Antwort vom Broker!");
        }
    }

    public void run() {

        try {
            JSONObject obj = new JSONObject();
            // String aisData = new String(Files.readAllBytes(Paths.get(filePath)));
            
           
            
            obj.put("type", "prediction-request");
            obj.put("data", AISfile);

            output.write(obj.toString() + "\n");
            output.flush();

            boolean finished = false;
            while (!finished) {
                try {
                    String s = input.readLine();
                    finished = parseBrokerResponse(s);
                }
                catch (SocketTimeoutException e) {
                    TextLog.getInstance().write("FEHLER: Der Broker hat nicht geantwortet nach " + readTimeout + " ms!");

                    // Quit over finished to perform cleanup
                    finished = true;
                    continue;
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
