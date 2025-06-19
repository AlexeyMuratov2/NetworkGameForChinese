package org.example;


import org.example.commands.CommandManager;
import org.example.database.AppConfig;
import org.example.database.DatabaseSetupRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        SpringContext.setContext(new AnnotationConfigApplicationContext(AppConfig.class));

        SpringContext.getBean(DatabaseSetupRepository.class);
        CommandManager.registerAllCommands();
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.submit(new ServerMain());
    }
}