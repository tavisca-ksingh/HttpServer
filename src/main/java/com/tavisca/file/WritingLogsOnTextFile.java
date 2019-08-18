package com.tavisca.file;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class WritingLogsOnTextFile {

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream fileOut = new PrintStream("./out.txt");
        System.setOut(fileOut);
        String line= "hello i m here";
        System.out.println("Email " + line + " is valid. Please input another one.");
        System.out.println("hrjkad " + line + " iyour chance ,man");
        System.out.println("nope " + line + " is vadj hello hi i m here.");
    }
}
