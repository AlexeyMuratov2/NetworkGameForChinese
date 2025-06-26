package org.example;


import org.example.commands.CommandManager;
import org.example.database.AppConfig;
import org.example.database.DatabaseSetupRepository;
import org.example.model.ApplicationContextHolder;
import org.example.network.ServerMain;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        new ApplicationContextHolder().setApplicationContext(context);

        context.getBean(DatabaseSetupRepository.class);
        context.getBean(CommandManager.class);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.submit(context.getBean(ServerMain.class));
    }
}