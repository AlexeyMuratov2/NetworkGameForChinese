package org.example;

import org.example.commands.CommandManager;
import org.example.controllers.LobbyPanelController;
import org.example.controllers.LoginPanelController;
import org.example.controllers.RegisterPanelController;
import org.example.model.AppConfig;
import org.example.model.ApplicationContextHolder;
import org.example.model.ClientConnectionHandler;
import org.example.view.MainFrame;
import org.example.view.Panels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    @Autowired
    CommandManager commandManager;

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        new ApplicationContextHolder().setApplicationContext(context);

        ExecutorService singleThreadedExecutor = Executors.newSingleThreadExecutor();
        ClientConnectionHandler client = context.getBean(ClientConnectionHandler.class);
        singleThreadedExecutor.submit(client);
        MainFrame mainFrame = context.getBean(MainFrame.class);
        System.out.println(mainFrame);
        mainFrame.switchTo(Panels.LOGIN);

        registerControllers(context);
    }

    private static void registerControllers(AnnotationConfigApplicationContext context){
        context.getBean(LoginPanelController.class);
        context.getBean(RegisterPanelController.class);
        context.getBean(LobbyPanelController.class);
    }
}