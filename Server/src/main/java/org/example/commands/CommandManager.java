package org.example.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandManager {
    private final Map<String, Command> commandsMap = new ConcurrentHashMap<>();

    public Command getCommand(String command) {
        return commandsMap.get(command);
    }

    @Autowired
    public CommandManager(
            EchoCommand echoCommand,
            GetLobbyCommand getLobbyCommand,
            HelloCommand helloCommand,
            LoginCommand loginCommand,
            RegisterCommand registerCommand,
            CreateLobbyCommand createLobbyCommand,
            UpdateLobbiesCommand updateLobbiesCommand,
            JoinLobbyCommand joinLobbyCommand
    ){
        commandsMap.put("hello", helloCommand);
        commandsMap.put("echo", echoCommand);
        commandsMap.put("login", loginCommand);
        commandsMap.put("register", registerCommand);
        commandsMap.put("getLobby", getLobbyCommand);
        commandsMap.put("createLobby", createLobbyCommand);
        commandsMap.put("updateLobbies", updateLobbiesCommand);
        commandsMap.put("joinLobby", joinLobbyCommand);
    }
}
