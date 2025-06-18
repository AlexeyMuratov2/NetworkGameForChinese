package org.example;


import org.example.commands.CommandManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        CommandManager.registerAllCommands();
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.submit(new ServerMain());
    }
}