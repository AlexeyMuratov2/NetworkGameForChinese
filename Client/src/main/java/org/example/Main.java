package org.example;

import org.example.commands.CommandManager;
import org.example.controllers.LoginPanelController;
import org.example.controllers.RegisterPanelController;
import org.example.model.ClientConnectionHandler;
import org.example.view.MainFrame;
import org.example.view.Panels;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args){
        CommandManager.registerCommands();
        ExecutorService singleThreadedExecutor = Executors.newSingleThreadExecutor();
        ClientConnectionHandler client = ClientConnectionHandler.getInstance();
        singleThreadedExecutor.submit(client);
        MainFrame mainFrame = MainFrame.getInstance();
        System.out.println(mainFrame);
        mainFrame.switchTo(Panels.LOGIN);

        registerControllers();
    }

    private static void registerControllers(){
        new LoginPanelController();
        new RegisterPanelController();
    }
}