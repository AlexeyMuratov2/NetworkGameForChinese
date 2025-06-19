package org.example.commands;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {
    private static Map<String, Command> commands = new ConcurrentHashMap<>();

    private static void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    public static void registerCommands() {
        addCommand("hello", new HelloCommand());
        addCommand("echo", new EchoCommand());
        addCommand("login", new LoginCommand());
    }
}
