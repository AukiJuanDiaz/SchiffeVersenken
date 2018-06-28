package sd.group03.broker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    static Broker broker;

    public static void main(String[] args) {


        try {
            broker = new Broker("resources/sampleConfig.json");
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
            socket = new ServerSocket(port);
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
