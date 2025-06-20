package org.example.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandManager {
    private Map<String, Command> commandMap = new ConcurrentHashMap<>();

    @Autowired
    public CommandManager(
            HelloCommand helloCommand,
            EchoCommand echoCommand,
            LoginCommand loginCommand,
            RegisterCommand registerCommand
    ) {
        commandMap.put("hello", helloCommand);
        commandMap.put("echo", echoCommand);
        commandMap.put("login", loginCommand);
        commandMap.put("register", registerCommand);
    }
    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }

}
