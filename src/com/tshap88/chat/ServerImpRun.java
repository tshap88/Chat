package com.tshap88.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerImpRun implements Runnable {
    Socket socket;
    BufferedReader in = null;
    PrintWriter out = null;
    String msgIn = null;

    public ServerImpRun(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            System.out.println("User connect: " + socket.getInetAddress().getHostName());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            while (true) {
                msgIn = in.readLine();
                if (msgIn.equals("out of the chat.")) {
                    socket.close();
                }

                out.println(msgIn);
                System.out.println("ok..");
                System.out.println(msgIn);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
