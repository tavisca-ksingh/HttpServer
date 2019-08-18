package com.tavisca.clientServerChat;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientServer {
    private Socket socket = null;
    private ServerSocket server = null;

    public ClientServer(int port)
    {
        try {
            server = new ServerSocket(port);
            System.out.println("Server Started");
            System.out.println("Waiting for connection....");
            socket = server.accept();
            System.out.println("Client Accepted");
            DataInputStream dataInputStream= new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage = "";
            String clientMessage = "";
            while (!serverMessage.equalsIgnoreCase("bye")){
                System.out.println("Waiting For Client .....");
                clientMessage = dataInputStream.readUTF();
                System.out.println("From Client : " + clientMessage);

                System.out.println("Enter your message from server : ");
                serverMessage = consoleReader.readLine();
                dataOutputStream.writeUTF(serverMessage);
                dataOutputStream.flush();

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ClientServer server = new ClientServer(80);
    }
}
