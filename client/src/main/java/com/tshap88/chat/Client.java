package com.tshap88.chat;

import java.net.*;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        try {
            System.out.println("Сlient starting.");
            socket = new Socket(ConfFile.getADDRESS(), ConfFile.getPORT());
            // System.out.println("Сonnection to the server:" + socket);
            Thread threadIn = new Thread(new InputThread(socket));
            Thread threadOut = new Thread(new OutputThread(socket));
            threadIn.start();
            threadOut.start();

        } catch (NullPointerException e) {
            System.out.println("Connection with server lost.");
        }catch (Exception x) {
            x.printStackTrace();
        }
    }
}
