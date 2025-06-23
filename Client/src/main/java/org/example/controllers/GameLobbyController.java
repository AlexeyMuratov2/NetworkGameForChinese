package org.example.controllers;

import org.example.model.ClientConnectionHandler;
import org.example.view.GameLobbyPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class GameLobbyController {
    private final GameLobbyPanel gameLobbyPanel;
    private final ClientConnectionHandler clientConnectionHandler;
    private final static Logger logger = Logger.getLogger(GameLobbyController.class.getName());

    @Autowired
    public GameLobbyController(GameLobbyPanel gameLobbyPanel,
                               ClientConnectionHandler clientConnectionHandler) {
        this.gameLobbyPanel = gameLobbyPanel;
        this.clientConnectionHandler = clientConnectionHandler;

        gameLobbyPanel.getReadyButton().addActionListener(e -> onReadyClick());
    }

    private void onReadyClick() {
        logger.info("Ready button clicked");
    }
}
