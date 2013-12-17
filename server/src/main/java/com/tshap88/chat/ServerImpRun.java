package com.tshap88.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerImpRun implements Runnable {
    ServerConnections serverConnections = new ServerConnections();
    private Socket socket;
    BufferedReader in = null;

    public ServerImpRun(ServerConnections connection) {
        this.serverConnections = connection;
        this.socket = connection.getLast();
    }

    @Override
    public void run() {

        try {
            System.out.println("User connect: " + socket.getInetAddress().getHostName());
            char[] buffer1 = new char[32];
            int charRead = 0;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!false || charRead > 0) {
                String str = "";
                charRead = in.read(buffer1);
                char[] buff = new char[charRead];

                if (charRead > 0) {
                    System.arraycopy(buffer1, 0, buff, 0, charRead);
                    str = str + new String(buff);
                }

                if (str.trim().equals("exit")) {
                    serverConnections.removeServerConnection(socket);
                    System.out.println("User is logged out of the chat");
                } else {
                    System.out.println(str);//"Server in:" +
                    System.out.println(serverConnections.listSocket.size());
                    for (Socket socket1 : serverConnections.getListSocket()) {
                        if (!socket1.equals(socket)) {
                            PrintWriter out = new PrintWriter(socket1.getOutputStream());
                            out.print(str);
                            out.flush();
                        }
                    }
                }
            }

           // in.close();
           // socket.close();

        } catch (NegativeArraySizeException e) {
            System.out.println("Connection with user has been interrupted");
            serverConnections.removeServerConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
