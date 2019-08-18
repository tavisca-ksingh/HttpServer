package com.tavisca.serverHandling;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class Response {
    StringBuilder HttpInfo= new StringBuilder();
    String fileName= "";
    Socket serverclient = null;
    BufferedOutputStream out;
    File myFile;

    public Response( Socket serverClient,String fileName)
    {
        this.serverclient = serverClient;
        this.fileName = fileName;
        try {
            out = new BufferedOutputStream(serverclient.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        myFile = new File(fileName);
    }



    public String ShowDataOnWeb() throws IOException
    {

        if(myFile.exists()) {
            out.write(("HTTP/1.1 200 OK\r\n").getBytes());
            generateHeader();
        }
        else{
            HttpInfo.append("HTTP/1.1 404\r\n");
            this.fileName= "badFile.html";
            generateHeader();
        }
        return HttpInfo.toString();
    }

    public void generateHeader() throws IOException {
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
