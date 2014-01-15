package com.tshap88.chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class InputThread implements Runnable {

    private Socket socket = null;
    private BufferedReader in = null;

    public InputThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Input");
        try {
            char[] buffer1 = new char[32];
            int charRead = 0;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true || charRead > 0) {
                String str = "";
                charRead = in.read(buffer1);
                char[] buff = new char[charRead];

                if (charRead > 0) {
                    System.arraycopy(buffer1, 0, buff, 0, charRead);
                    str = str + new String(buff);
                }

                if (!new String(buffer1).trim().equals("exit")) {
                    System.out.println(str);
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
