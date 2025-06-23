package org.example.commands;

import org.example.network.GameSession;
import org.example.network.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinLobbyCommand implements Command{
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
        GameSession session = sessionManager.getSessionByName(sessionName);
        session.addPlayer(username);
        session.updateLobbyInfoForAllUsers();
        return "lobbyJoined success " + session.getDisplayName();
    }
}
