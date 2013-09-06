package com.tshap88.chat;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;


public class ExitClient extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

}
