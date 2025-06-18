package org.example;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ServerMain implements Runnable{
    private static final int PORT = 12345;
    private static final int MAX_CLIENT = 10;
    private static final Logger logger = Logger.getLogger(ServerMain.class.getName());
    @Override
    public void run() {
        ExecutorService clientPool = Executors.newFixedThreadPool(MAX_CLIENT);
        logger.info("Server started");
        try(ServerSocket serverSocket = new ServerSocket(PORT);){
            while (true){
                Socket clientSocket = serverSocket.accept();
                logger.info("Client connected");
                clientPool.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            clientPool.shutdown();
        }
    }
}
