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
            while(true){
                msgOut = kb.readLine();
                out.println(msgOut);
                out.flush();
                msgIn = in.readLine();
                System.out.println("In: " + msgIn);
            }
           /* while ( () != null) {

            }   */
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
