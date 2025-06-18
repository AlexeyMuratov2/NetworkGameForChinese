package org.example;

import org.example.commands.CommandManager;
import org.example.view.MainFrame;
import org.example.view.Panels;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args){
        CommandManager.registerCommands();
        ExecutorService singleThreadedExecutor = Executors.newSingleThreadExecutor();
        ClientConnectionHandler client = new ClientConnectionHandler();
        singleThreadedExecutor.submit(client);
//        client.sendMessage("hello");
//        client.sendMessage("echo сам себе поотвечай");
//        client.stop();
        MainFrame mainFrame = new MainFrame();
        mainFrame.switchTo(Panels.LOGIN);
    }
}