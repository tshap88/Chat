package com.tshap88.chat;

import java.io.*;
import java.util.*;

public class ConfFile {
    public static int PORT;
    public static String ADDRESS;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/home/tania/chat/properties.txt"));
        PORT = Integer.parseInt(properties.getProperty("PORT"));
            ADDRESS = properties.getProperty("ADDRESS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
