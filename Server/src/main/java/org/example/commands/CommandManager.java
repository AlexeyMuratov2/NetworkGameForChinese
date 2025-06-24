package org.example.commands;

import org.example.commands.clientRegistrationCommands.LoginCommand;
import org.example.commands.clientRegistrationCommands.RegisterCommand;
import org.example.commands.lobbiesCommands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandManager {
    private final Map<String, Command> commandsMap = new ConcurrentHashMap<>();

    public Command getCommand(String command) {
        return commandsMap.get(command);
    }

    @Autowired
    public CommandManager(List<Command> commands){
        for(Command command : commands){
            commandsMap.put(command.getCommandName(), command);
        }
    }
}
