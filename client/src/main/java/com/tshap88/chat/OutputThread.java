package com.tshap88.chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class OutputThread implements Runnable {

    private Socket socket = null;
    private PrintWriter out = null;
    private String msgOut = null;


    public OutputThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String username="";
            boolean exit = true;
            out = new PrintWriter(socket.getOutputStream());
            BufferedReader bk = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Your username:");
            username = bk.readLine();
            while (exit) {
                msgOut = bk.readLine();
                if (!msgOut.trim().equals("exit")) {
                    System.out.println(username + ": " + msgOut);//"This is out:" + msgOut);
                    out.println(username + ": " +msgOut);
                    out.flush();
                } else {
                    out.println(username + ": " +msgOut);
                    out.flush();
                    exit = false;
                }
            }

            out.close();
            bk.close();
            socket.close();


        } catch (NullPointerException e) {
            System.out.println("Connection with server has been interrupted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
