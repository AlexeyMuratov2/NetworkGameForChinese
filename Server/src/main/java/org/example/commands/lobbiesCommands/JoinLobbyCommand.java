package org.example.commands.lobbiesCommands;

import org.example.commands.Command;
import org.example.model.MessageFactory;
import org.example.network.GameSession;
import org.example.network.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinLobbyCommand implements Command {
    private final SessionManager sessionManager;

    @Autowired
    public JoinLobbyCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public String execute(String args) {
        String[] parts = args.split(" ");
        String username = parts[0];
        String sessionName = parts[1];
        String firstUserInLobby = sessionName.split(",")[0];
        GameSession session = sessionManager.getSessionByName(firstUserInLobby);
        session.addPlayer(username);
        session.updateLobbyInfoForAllUsers();
        return MessageFactory.lobbyJoined(true, session.getClientHandlersInSession());
    }

    @Override
    public String getCommandName(){
        return "joinLobby";
    }
}
