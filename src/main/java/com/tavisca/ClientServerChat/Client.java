package com.tavisca.ClientServerChat;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream  output = null;
    public Client(String address , int port){
        try {
            socket = new Socket(address,port);
            if(socket.isConnected())
                System.out.println("Connected to server");

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage = "";
            String clientMessage = "";
            while(!clientMessage.equalsIgnoreCase("bye")){
                System.out.println("Enter your Message : ");
                clientMessage = consoleReader.readLine();
                output.writeUTF(clientMessage);
                output.flush();
                System.out.println("Waiting for server ...");
                serverMessage= input.readUTF();
                System.out.println("From Server : " + serverMessage );
                System.out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = "";

    }
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 80);
    }
}
