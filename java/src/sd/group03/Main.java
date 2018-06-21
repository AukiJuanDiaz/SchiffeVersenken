package sd.group03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    static Broker broker;

    public static void main(String[] args) {

        Path configPath = Paths.get("src/sampleConfig.json");

        try {
            broker = new Broker(configPath.toString());
            startServer();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    private static void startServer() {

        ServerSocket socket = null;
        short port = 9812;

        System.out.println("Starting Server");

        try {
            socket = new ServerSocket(9812);
            System.out.println("Listening on Port: " + port);
        }
        catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
            return;
        }

        while(true) {

            try {
                Socket connection = socket.accept();
                System.out.println("New connection from: " + connection.getRemoteSocketAddress());
                (new Thread(new BrokerNetworkConnection(broker, connection))).start();
            }
            catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
                return;
            }

        }
    }
}
