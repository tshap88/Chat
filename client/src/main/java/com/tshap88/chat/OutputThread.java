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
            boolean exit = true;
            out = new PrintWriter(socket.getOutputStream());
            BufferedReader bk = new BufferedReader(new InputStreamReader(System.in));
            while (exit) {
                msgOut = bk.readLine();
                if (!msgOut.trim().equals("exit")) {
                    System.out.println("This is out:" + msgOut);
                    out.println(msgOut);
                    out.flush();
                } else {
                    out.println(msgOut);
                    out.flush();
                    exit = false;
                    socket.close();
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Connection with server has been interrupted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
