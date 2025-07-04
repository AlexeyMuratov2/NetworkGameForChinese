package org.example.network;

import lombok.Getter;
import lombok.Setter;
import org.example.commands.Command;
import org.example.commands.CommandManager;
import org.example.model.ApplicationContextHolder;
import org.example.model.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;


public class ClientHandler implements Runnable{
    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    private final DataOutputStream dataOutputStream;
    private final DataInputStream dataInputStream;
    private final Socket socket;
    @Getter @Setter
    private String username;
    @Getter
    private Integer PORT;
    @Getter @Setter
    private boolean isReady = false;
    private volatile boolean running = true;
    private CommandManager commandManager;
    private ClientManager clientManager = ApplicationContextHolder.getBean(ClientManager.class);
    private MainGameManager mainGameManager = ApplicationContextHolder.getBean(MainGameManager.class);

    public ClientHandler(Socket socket, CommandManager commandManager) throws IOException {
        this.commandManager = commandManager;
        this.socket = socket;
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        this.PORT = socket.getPort();
        initializeInClientManager();
    }

    private void initializeInClientManager(){
        clientManager.addClient(PORT, this);
    }


    @Override
    public void run() {
        logger.info("Client connected " + socket.toString());

        try {
            while (running) {
                String message = dataInputStream.readUTF(); // используй поле, не создавай новое
                logger.info("Message received: " + message);
                handleMessage(message);
            }

        } catch (IOException e) {
            logger.severe("Error in ClientHandler: " + e.getMessage());

        } finally {
            try {
                socket.close();
                dataOutputStream.close();
                dataInputStream.close();
                logger.info("Client disconnected" + this.username);
                mainGameManager.onClientDisconnect(this);
            } catch (IOException e) {
                logger.severe(e.getMessage());
            }
        }
    }

    public synchronized void sendMessage(String message) {
        try {
            if (!message.equals(MessageFactory.ignoreMessage())) {
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
                logger.info("Message sent: " + message);
            }
        } catch (IOException e) {
            logger.severe("Error sending message: " + e.getMessage());
        }
    }

    private void handleMessage(String message) {
        String[] parts = message.trim().split(" ", 2);
        String commandName = parts[0];
        String args = parts.length > 1 ? parts[1] : "";
        Command commandObject = commandManager.getCommand(commandName);
        if (commandObject != null) {
            String response = commandObject.execute(args);
            logger.info("Executed command: " + commandName);
            logger.info("Response: " + response);
            sendMessage(response);
        } else {
            sendMessage("Unknown command: " + commandName);
        }
    }

    private void close(){
        try {
            running = false;
            socket.close();
            dataOutputStream.close();
            dataInputStream.close();
            mainGameManager.onClientDisconnect(this);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
