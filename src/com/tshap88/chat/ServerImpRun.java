package com.tshap88.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerImpRun implements Runnable {
    ServerConnections serverConnections;
    Socket socket;
    BufferedReader in = null;
    String msgIn = null;

    public ServerImpRun(ServerConnections connection) {
        this.serverConnections = connection;
        this.socket = connection.getLast();
    }

    @Override
    public void run() {

        try {

            System.out.println("User connect: " + socket.getInetAddress().getHostName());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                msgIn = in.readLine();
                System.out.println("Server in:" + msgIn);
                for (Socket socket1 : serverConnections.getListSocket()) {
                    PrintWriter out = new PrintWriter(socket1.getOutputStream());
                    out.println(msgIn);
                    out.flush();
                    out.close();

                }
            }
          /*  while (true) {
                msgIn = in.readLine();
                if (msgIn.equals("out of the chat.")) {
                    socket.close();
                }

                out.println(msgIn);
                System.out.println("ok..");
                System.out.println(msgIn);
                out.flush();
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
