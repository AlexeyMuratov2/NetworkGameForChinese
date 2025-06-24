package org.example.network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainGameManager {
    private final ClientManager clientManager;
    private final SessionManager sessionManager;

    @Autowired
    public MainGameManager(ClientManager clientManager, SessionManager sessionManager) {
        this.clientManager = clientManager;
        this.sessionManager = sessionManager;
    }

    public void onClientDisconnect(ClientHandler clientHandler){
        clientManager.removeClient(clientHandler.getPORT());
        sessionManager.removeFromSessionByUsername(clientHandler.getUsername());
    }
}
