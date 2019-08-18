package com.tavisca.serverHandling;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    Log logger = null;
    private Log(){
        if (logger == null){
            logger = new Log();
        }
    }
    public static Logger getLogger() throws FileNotFoundException {
        LogManager logManager = LogManager.getLogManager();
        Logger log = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
        FileHandler fh;
        {
            try {
                fh = new FileHandler("log/MyLogFile.log",true);
                log.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return log;
    }
}
