package com.tavisca.serverHandling;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Date;

public class FileHandling {
    StringBuilder HttpInfo= new StringBuilder();
    File file ;

   public Socket serverclient = null;
   BufferedOutputStream out;

    {
        try {
            out = new BufferedOutputStream(serverclient.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String fileName, Socket serverClient) throws IOException
    {
        this.serverclient= serverClient;

        file = new File(fileName);
        if(file.exists()) {
            out.write(("HTTP/1.1 200 OK\r\n").getBytes());
            DataToSentToClient(fileName);
        }
        else{
            HttpInfo.append("HTTP/1.1 404\r\n");
            DataToSentToClient("badFile.html");
        }
        return HttpInfo.toString();
    }

    public void DataToSentToClient(String fileName) throws IOException {
        File myFile = new File(fileName);
        String extension =  getExtension(fileName);
        out.write(("Server: Java HTTP Server/v1.0\r\n").getBytes());
        out.write(("Date: " + (new Date()).toString()+ "\r\n").getBytes());
        out.write(("Content-type: " + RequestParser.ExtensionMap.get(extension) + "\r\n" ).getBytes());
        out.write(("Content-length: " + myFile.length()+ "\r\n").getBytes());
        out.write(("\r\n").getBytes());
        out.write(fileReader(fileName));
        out.flush();
    }

    public byte [] fileReader(String fileName){
        byte [] b= null;
        try {
            FileInputStream fin = new FileInputStream(fileName);
             b = new byte[fin.available()];
            fin.read(b);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    return b;
    }



    private String getExtension(String fileName) {
        String extension = fileName.split("\\.")[1];
        return extension;
    }
}
