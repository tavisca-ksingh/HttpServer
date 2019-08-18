package com.tavisca.serverHandling;

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
            staringServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void staringServer(int port) throws IOException {
        server = new ServerSocket(port);
        System.out.println("Server Started");
        System.out.println("Waiting for connection....");
        int counter=0;
        while (true) {
            counter++;
            serverClient = server.accept();
            System.out.println(" >> Client Acepted \r\n >>"  + " Client No : " + counter + " started!");
            ServerClientThread sct = new ServerClientThread(serverClient,counter);
            sct.start();
        }
    }

    public static void main(String[] args) {
        Server server =new Server(5000);
    }
}

