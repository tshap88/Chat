package com.tshap88.chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Input implements Runnable {

    private Socket socket = null;
    private BufferedReader in = null;


    public Input(Socket socket) {
        this.socket = socket;
    }

    char[] buffer = new char[32];

    @Override
    public void run() {
        System.out.println("Input");
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                int charServer = in.read(buffer);
                System.arraycopy(buffer, 0, buffer, 0, charServer);
                System.out.println("This is in:" + new String(buffer));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
