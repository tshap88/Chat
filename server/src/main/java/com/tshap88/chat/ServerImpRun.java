package com.tshap88.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ServerImpRun implements Runnable {

    private ServerConnections serverConnections = new ServerConnections();
    private Socket socket;
    private BufferedReader in = null;
    private String username = "";


    public ServerImpRun(ServerConnections connection) {
        this.serverConnections = connection;
        this.socket = connection.getLast();
    }

    @Override
    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            username = in.readLine();
            serverConnections.putServerConnection(username.trim(), socket);

            System.out.println(serverConnections.getSizeMap());
            System.out.println("User connect: " + username + " " + socket.getInetAddress().getHostName());

            char[] buffer1 = new char[32];
            int charRead = 0;

            while (!false) {
                String str = "";
                charRead = in.read(buffer1);
                char[] buff = new char[charRead];

                if (charRead > 0) {
                    System.arraycopy(buffer1, 0, buff, 0, charRead);
                    str = str + new String(buff);
                }

                // вычитываю из строки имя клиента которому нужно отправить сообщение
                int num = str.indexOf(":");
                String name = str.substring(0, num);

                if (str.trim().equals("exit")) {
                    serverConnections.removeServerConnection(socket);
                    System.out.println("User is logged out of the chat");

                } else {
                    System.out.println(str);//"Server in:" +
                    //  System.out.println(serverConnections.listSocket.size());

                    Set<Map.Entry<String, Socket>> set = serverConnections.getSetMap().entrySet();
                    for (Map.Entry<String, Socket> me : set) {
                        if (me.getKey().equals(name)) {
                            PrintWriter out = new PrintWriter(me.getValue().getOutputStream());
                            out.print(str);
                            out.flush();

                            System.out.println(me.getKey() + "fffffffffffff" + name);
                        }
                    }

                    System.out.println("who: " + username);
                    System.out.println("whom: " + name);

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
