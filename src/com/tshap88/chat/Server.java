package com.tshap88.chat;
import java.net.*;
import java.io.*;

public class Server {
    private static int port = 5343;                                     //same here - remove hard code
    public static void main (String [] args) {

        ServerSocket ssocket;
        try {
            ssocket = new ServerSocket(port);
            System.out.println("Server is up");

            while (true) {
                Socket socket = ssocket.accept();
                Thread threads = new Thread(new com.tshap88.chat.ServerImpRun(socket));
                threads.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
