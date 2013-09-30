package com.tshap88.chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Output implements Runnable {

    private Socket socket = null;
    private PrintWriter out = null;
    private String msgOut = null;

    public Output(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream());
            BufferedReader bk = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                msgOut = bk.readLine();
                System.out.println("This is out:" + msgOut);
                out.println(msgOut);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
