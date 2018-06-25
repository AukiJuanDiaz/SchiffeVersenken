package sd.group03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    static Broker broker;

    public static void main(String[] args) {

        Path configPath;

        if(args.length > 0) {
            configPath = Paths.get(args[0]);
        }
        else {
            configPath = Paths.get("src/sampleConfig.json");
        }

        System.out.println("Loading config from file: " + configPath.toString());

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

        ServerSocket socket;
        short port = 9812;

        System.out.println("Starting Server");

        try {
            socket = new ServerSocket(9812);
            System.out.println("Listening on Port: " + port);
        }
        catch (IOException e) {
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
                e.printStackTrace();
                return;
            }

        }
    }
}
