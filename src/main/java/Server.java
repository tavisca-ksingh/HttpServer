import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket serverClient = null;
    private ServerSocket server = null;
    private BufferedInputStream bufferedInputStream = null;

    public Server(int port){
        try {
            server = new ServerSocket(port);
            System.out.println("Server Started");

            System.out.println("Waiting for connection....");
            int counter=0;

            while (true) {
                counter++;
                serverClient = server.accept();
                System.out.println(" >> Client Acepted >> " + "Client No:" + counter + " started!");
                ServerClientThread sct = new ServerClientThread(serverClient,counter);
                sct.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server =new Server(80);
    }
}

