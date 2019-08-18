package com.tavisca.serverHandling;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;

public class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    BufferedInputStream bufferedInputStream;

    ServerClientThread(Socket inSocket, int counter){
        serverClient = inSocket;
        clientNo=counter;
    }

    @Override
    public void run() {
        try {
            bufferedInputStream = new BufferedInputStream(serverClient.getInputStream());
            webPageProcessing(bufferedInputStream);
           System.out.println("Thread id : " + Thread.currentThread().getId());
           System.out.println("Client : " + clientNo + " exit!! ");
            serverClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void webPageProcessing(BufferedInputStream bufferedInputStream) throws IOException {

        String fileName= RequestParser.getFileName(bufferedInputStream);
        if(fileName.equalsIgnoreCase("index.html") || fileName.equals(""))
        {
            Response response=new Response(serverClient,"index.html");
            response.ShowDataOnWeb();
        }
        else
        {
            Response response=new Response(serverClient,fileName);
            //  serverClient.getOutputStream().write(header.getBytes());
            response.ShowDataOnWeb();
        }

    }
}
