package com.tshap88.chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Input implements Runnable {

    private Socket socket = null;
    private BufferedReader in = null;

    public Input(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Input");
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            char[] buffer = new char[32];
            while (true) {

                int charServer = in.read(buffer);

                if (charServer > 0) {
                    char[] buffer1 = new char[charServer];
                    System.arraycopy(buffer, 0, buffer1, 0, charServer);
                    if (!new String(buffer1).trim().equals("exit")) {
                        System.out.print("This is in:" + new String(buffer1));
                    }
                    // System.out.print("This is in:" + new String(buffer1));
                }
            }
        } catch (NegativeArraySizeException e) {
            System.out.println("server disconnect");
        } catch (SocketException e) {
            System.out.println("Bye");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
