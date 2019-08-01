package com.tavisca.serverHAndling;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

public class FileHandling {
    StringBuilder HttpInfo= new StringBuilder();
    File file ;



    public String  readFile(String fileName) throws IOException
    {
        file = new File(fileName);
        if(file.exists()) {
            HttpInfo.append("HTTP/1.1 200 OK\r\n");
            DataToSentToClient(fileName);
        }
        else{
            HttpInfo.append("HTTP/1.1 404\r\n");
            DataToSentToClient("badFile.html");
        }
        return HttpInfo.toString();
    }


    public String DataToSentToClient(String fileName) throws IOException {
        File myFile = new File(fileName);
        HttpInfo.append("Server: Java HTTP Server/v1.0\r\n");
        HttpInfo.append("Date: " + LocalDateTime.now()+ "\r\n");
        HttpInfo.append("Content-type: text/html\r\n");
        HttpInfo.append("Content-length: " + myFile.length()+ "\r\n");
        HttpInfo.append("\r\n");
        HttpInfo.append(String.join("",Files.readAllLines(myFile.toPath())) + "\r\n");
        return HttpInfo.toString();
    }
}
