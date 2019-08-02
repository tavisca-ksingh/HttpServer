package com.tavisca.serverHAndling;
import java.io.*;
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
            OutputInString(bufferedInputStream);

            System.out.println("Client : " + clientNo + " exit!! ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    FileHandling fileHandling=new FileHandling();

    private void OutputInString(BufferedInputStream bufferedInputStream) throws IOException {
        byte[] data = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(data);
        String readString = new String(data);
        String [] value = readString.split(" ");


        if(value[1].equalsIgnoreCase("/index.html") || value[1].equals("/"))
        {
            String header = fileHandling.readFile("index.html");
            serverClient.getOutputStream().write(header.getBytes());
        }
        else
        {
            String fileName= value[1].substring(1);

            String header = fileHandling.readFile(fileName);
            System.out.println("************************");
            System.out.println(header);
            System.out.println("************************");
            serverClient.getOutputStream().write(header.getBytes());
        }
        serverClient.close();
        bufferedInputStream.close();
    }
}
