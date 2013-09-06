package com.tshap88.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientImpRun implements Runnable {
    Socket socket;
    BufferedReader kb = null;
    BufferedReader in = null;
    PrintWriter out = null;
    String msgIn = null;
    String msgOut = null;
    int count = 0;

    public ClientImpRun(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Сonnection to the server was successful.");
            kb = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            while (true) {

                msgOut = kb.readLine();
                if (msgOut.isEmpty()) {
                    count++;

                    if (count == 2) {
                        out.println("out of the chat.");
                        out.flush();
                        System.exit(0);
                    }
                } else {
                    count = 0;
                }
                out.println(msgOut);
                out.flush();

                msgIn = in.readLine();
                if (!msgIn.isEmpty()) {
                    System.out.println("In: " + msgIn);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
