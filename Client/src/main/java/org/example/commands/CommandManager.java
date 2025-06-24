package org.example.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandManager {
    private Map<String, Command> commandMap = new ConcurrentHashMap<>();

    @Autowired
    public CommandManager(List<Command> commands) {
        for(Command command : commands) {
            commandMap.put(command.getName(), command);
        }
    }
    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }

}
