package com.tshap88.chat;

import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) {

        ServerSocket ssocket;
/*        byte[] bytes = "weret".getBytes();
        byte[] bytes1 = new byte[bytes.length+10];
        System.arraycopy(bytes, 0, bytes1, 0, bytes.length);
        System.out.println(new String(bytes1));
        byte[] buffer = new byte[bytes.length];
        System.arraycopy(bytes, 0, bytes, 0, bytes.length);
        System.out.println(new String(bytes));     */
        try {
            ServerConnections sc;
            ssocket = new ServerSocket(ConfFile.getPORT());
            System.out.println("Server is up: " + ssocket.getInetAddress().getHostName());

            while (true) {
                sc = new ServerConnections();
                Socket socket = ssocket.accept();
                sc.addServerConnection(socket);
                Thread threads = new Thread(new ServerImpRun(sc));
                threads.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
