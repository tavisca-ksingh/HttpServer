package com.tavisca.serverHandling;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class RequestParser {
    public static final Map<String, String> ExtensionMap = Collections.unmodifiableMap(
            new HashMap<String, String>(){{
                put("html", "text/html");
                put("htm", "text/html");
                put("jpeg", "image/jpeg");
                put("jpg", "image/jpeg");
                put("png", "image/png");
                put("ico", "image/x-icon");
                put("txt", "plain/text");
            }}
    );

    public static String getFileName(BufferedInputStream InputStream) throws IOException {
        String fileName="";
        return fileName(InputStream, fileName);
    }

    private static String fileName(BufferedInputStream InputStream, String fileName) throws IOException {
        byte[] data = new byte[InputStream.available()];
        InputStream.read(data);
        String readString = new String(data);
        StringTokenizer st = new StringTokenizer(readString);
        if(readString!="" && st.hasMoreTokens()) {
                String methodName = st.nextToken().trim();
            if(st.hasMoreTokens())
            fileName = st.nextToken().replace("/", "");
        }
        return fileName;
    }


}
