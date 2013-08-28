package com.tshap88.chat;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        try {
            System.out.println("Сlient starting.");
            socket = new Socket(ConfFile.ADDRESS, ConfFile.PORT);
           // System.out.println("Сonnection to the server:" + socket);
            Thread threadc = new Thread(new ClientImpRun(socket));
            threadc.start();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
