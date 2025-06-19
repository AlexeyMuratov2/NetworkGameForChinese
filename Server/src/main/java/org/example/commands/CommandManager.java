package org.example.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {
    private static final Map<String, Command> commands = new ConcurrentHashMap<>();

    public static Command getCommand(String command) {
        return commands.get(command);
    }

    private static void addCommand(String command, Command commandObject) {
        commands.put(command, commandObject);
    }

    public static void registerAllCommands() {
        addCommand("hello", new HelloCommand());
        addCommand("echo", new EchoCommand());
        addCommand("login", new LoginCommand());
    }
}
