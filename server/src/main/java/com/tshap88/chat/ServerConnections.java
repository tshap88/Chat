package com.tshap88.chat;


import java.net.Socket;
import java.util.ArrayList;

public class ServerConnections {

    ArrayList<Socket> listSocket = null;

    public ServerConnections() {
       listSocket = new ArrayList<Socket>();
    }

    public synchronized ArrayList<Socket> getListSocket() {
        return listSocket;
    }

    public synchronized void addServerConnection(Socket socket) {
        listSocket.add(socket);
    }

    public synchronized Socket getLast() {
        return listSocket.get(listSocket.size() - 1);
    }

    public synchronized void removeServerConnection(Socket socket) {
        listSocket.remove(socket);
    }
}
