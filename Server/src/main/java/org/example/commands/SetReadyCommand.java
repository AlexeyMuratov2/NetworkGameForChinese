package org.example.commands;

import org.example.model.MessageFactory;
import org.example.network.ClientManager;
import org.example.network.GameSession;
import org.example.network.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetReadyCommand implements Command{
    private final ClientManager clientManager;
    private final SessionManager sessionManager;

    @Autowired
    public SetReadyCommand(ClientManager clientManager, SessionManager sessionManager) {
        this.clientManager = clientManager;
        this.sessionManager = sessionManager;
    }

    @Override
    public String execute(String args) {
        String[] parts = args.split(" ");
        String username = parts[0];
        boolean isReady = parts[1].equals("true");
        clientManager.getClientHandlerByName(username).setReady(isReady);
        GameSession session = sessionManager.getSessionByName(username);
        session.sendMessageToSession(MessageFactory.updateLobbyInfo(session.getClientHandlersInSession()));
        return MessageFactory.ignoreMessage();
    }
}
