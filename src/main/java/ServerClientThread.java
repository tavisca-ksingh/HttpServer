import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    ServerClientThread(Socket inSocket, int counter){
        serverClient = inSocket;
        clientNo=counter;
    }


    @Override
    public void run() {

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(serverClient.getInputStream());
            byte[] data = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(data);
            String readString = new String(data);
            System.out.println(readString);
            bufferedInputStream.close();
            serverClient.close();
            System.out.println("Client -" + clientNo + " exit!! ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
