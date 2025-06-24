package org.example.commands.lobbiesCommands;

import org.example.commands.Command;
import org.example.model.MessageFactory;
import org.example.network.GameSession;
import org.example.network.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateLobbyCommand implements Command {
    @Autowired
    private SessionManager sessionManager;

    @Override
    public String execute(String args) {
        GameSession session = sessionManager.createSession();
        session.addPlayer(args);
        return MessageFactory.lobbyJoined(true, session.getClientHandlersInSession());
    }

    @Override
    public String getCommandName(){
        return "createLobby";
    }
}
