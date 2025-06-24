package org.example.commands;

import org.example.model.ClientContext;
import org.example.view.GameLobbyPanel;
import org.example.view.MainFrame;
import org.example.view.Panels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UpdateLobbyInfoCommand implements Command {
    private static final Logger logger = Logger.getLogger(UpdateLobbyInfoCommand.class.getName());
    private final ClientContext clientContext;
    private final GameLobbyPanel gameLobbyPanel;
    private final MainFrame mainFrame;

    @Autowired
    public UpdateLobbyInfoCommand(ClientContext clientContext, GameLobbyPanel gameLobbyPanel, MainFrame mainFrame) {
        this.clientContext = clientContext;
        this.gameLobbyPanel = gameLobbyPanel;
        this.mainFrame = mainFrame;
    }

    @Override
    public void execute(String args) {
        String[] parts = args.split(",");
        gameLobbyPanel.clearAllSlots();
        for (int i = 0; i < parts.length; i++) {
            gameLobbyPanel.updatePlayerSlot(i, parts[i]);
            logger.info("set player " + i + " to " + parts[i]);
        }
    }
}
