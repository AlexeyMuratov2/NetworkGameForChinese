package org.example;

import org.example.commands.Command;
import org.example.commands.CommandManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class ClientConnectionHandler implements Runnable {
    private static final int PORT = 12345;
    private static final String HOST = "localhost";
    private static final Logger logger = Logger.getLogger(ClientConnectionHandler.class.getName());
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private volatile boolean running = true;

    private final BlockingQueue<String> outboundMessages = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        try (Socket socket = new Socket(HOST, PORT)) {
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
            logger.info("Connected to server");

            Thread readerThread = new Thread(this::readMessages, "Client-Reader");
            readerThread.start();

            while (running) {
                String message = outboundMessages.take();
                logger.info("Sending message: " + message);
                outputStream.writeUTF(message);
            }

        } catch (Exception e) {
            logger.severe(e.getMessage());
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                logger.severe(e.getMessage());
            }
        }
    }

    public void sendMessage(String message) {
        outboundMessages.offer(message);
    }

    private void readMessages() {
        while (running) {
            try {
                String message = inputStream.readUTF();
                handleMessage(message);
            } catch (IOException e) {
                logger.severe(e.getMessage());
            }
        }
    }

    private void handleMessage(String message) {
        String[] parts = message.trim().split(" ", 2);
        String commandName = parts[0];
        String args = parts.length > 1 ? parts[1] : "";
        Command commandObject = CommandManager.getCommand(commandName);
        logger.info("Executing command: " + commandName + " with args: " + args);
        if (commandObject != null) {
            commandObject.execute(args);
        }else {
            throw new IllegalArgumentException("Unknown command: " + commandName);
        }
    }

    public void stop() {
        running = false;
        try {
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
