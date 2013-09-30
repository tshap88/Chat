package com.tshap88.chat;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        try {
            System.out.println("Сlient starting.");
            socket = new Socket(ConfFile.getADDRESS(), ConfFile.getPORT());
            // System.out.println("Сonnection to the server:" + socket);
            Thread threadIn = new Thread(new Input(socket));
            Thread threadOut = new Thread(new Output(socket));
            threadIn.start();
            threadOut.start();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
