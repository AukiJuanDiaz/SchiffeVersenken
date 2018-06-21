package sd.group03;

import org.json.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GUINetworkConnection implements Runnable {

    private Socket socket;
    private BufferedWriter output;
    private BufferedReader input;
    private String filePath;

    public GUINetworkConnection(String host, short port, String path) throws IOException {
        socket = new Socket(InetAddress.getByName(host), port);
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        filePath = path;
    }

    public void run() {

        try {
            JSONObject obj = new JSONObject();
            String aisData = new String(Files.readAllBytes(Paths.get(filePath)));

            obj.put("type", "prediction-request");
            obj.put("data", aisData);

            output.write(obj.toString() + "\n");
            output.flush();

            // Only for testing

            boolean finished = false;
            while (!finished) {
                JSONObject response = new JSONObject(input.readLine());

                String type = response.getString("type");

                if ("message".equals(type)) {
                    String msg = response.getString("message");
                    TextLog.getInstance().write(msg);
                } else if ("result".equals(type)) {
                    double ett = response.getDouble("ett");
                    TextLog.getInstance().write("ETT: " + ett);
                    finished = true;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
