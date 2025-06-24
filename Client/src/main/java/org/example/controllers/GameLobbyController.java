package org.example.controllers;

import org.example.model.ClientConnectionHandler;
import org.example.model.ClientContext;
import org.example.model.MessageFactory;
import org.example.view.GameLobbyPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class GameLobbyController {
    private final GameLobbyPanel gameLobbyPanel;
    private final ClientConnectionHandler clientConnectionHandler;
    private final static Logger logger = Logger.getLogger(GameLobbyController.class.getName());
    private final ClientContext clientContext;

    @Autowired
    public GameLobbyController(GameLobbyPanel gameLobbyPanel,
                               ClientConnectionHandler clientConnectionHandler, ClientContext clientContext) {
        this.gameLobbyPanel = gameLobbyPanel;
        this.clientConnectionHandler = clientConnectionHandler;
        this.clientContext = clientContext;

        gameLobbyPanel.getReadyButton().addActionListener(e -> onReadyClick());
    }

    private void onReadyClick() {
        clientContext.setReady(!clientContext.isReady());
        String msg = MessageFactory.setReady(clientContext.getUsername(), clientContext.isReady());
        clientConnectionHandler.sendMessage(msg);
    }
}
