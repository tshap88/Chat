package com.tshap88.chat;
import java.net.Socket;

public class ServerImpRun implements Runnable {
    Socket socket;

    public ServerImpRun(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
