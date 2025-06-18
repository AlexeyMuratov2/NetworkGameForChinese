package org.example;

import java.net.Socket;
import java.util.logging.Logger;

public class ClientHandler implements Runnable{
    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    private final Socket socket;


    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
}
