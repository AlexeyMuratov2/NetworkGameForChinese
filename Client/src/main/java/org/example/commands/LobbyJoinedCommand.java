package org.example.commands;

import org.example.controllers.LobbyPanelController;
import org.example.model.ClientContext;
import org.example.view.GameLobbyPanel;
import org.example.view.MainFrame;
import org.example.view.Panels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class LobbyJoinedCommand implements Command{
    private static final Logger logger = Logger.getLogger(LobbyJoinedCommand.class.getName());
    private final ClientContext clientContext;
    private final GameLobbyPanel gameLobbyPanel;
    private final MainFrame mainFrame;

    @Autowired
    public LobbyJoinedCommand(ClientContext clientContext, GameLobbyPanel gameLobbyPanel, MainFrame mainFrame) {
        this.clientContext = clientContext;
        this.gameLobbyPanel = gameLobbyPanel;
        this.mainFrame = mainFrame;
    }

    @Override
    public void execute(String args) {
        String[] parts = args.split(" ");
        String result = parts[0];
        String[] players = parts[1].split(",");
        if (result.equals("success")) {
            mainFrame.switchTo(Panels.GAME_LOBBY);
            for (int i = 0; i < players.length; i++) {
                gameLobbyPanel.updatePlayerSlot(i, players[i]);
                logger.info("set player " + i + " to " + players[i]);
            }
        }
        else {
            logger.info("lobby joined failed, try again");
        }
    }
}
