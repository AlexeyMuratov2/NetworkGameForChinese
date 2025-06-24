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
        // args: "true(username1,false,username2,true,)"
        int bracketStart = args.indexOf('(');
        if (bracketStart == -1 || !args.endsWith(")")) {
            logger.warning("Malformed lobbyJoined message: " + args);
            return;
        }

        String result = args.substring(0, bracketStart).trim();  // "true" или "false"
        String playersRaw = args.substring(bracketStart + 1, args.length() - 1);  // без скобок

        if (result.equals("true")) {
            mainFrame.switchTo(Panels.GAME_LOBBY);
            String[] tokens = playersRaw.split(",");

            for (int i = 0; i < tokens.length - 1; i += 2) {
                String username = tokens[i];
                boolean isReady = Boolean.parseBoolean(tokens[i + 1]);
                gameLobbyPanel.updatePlayerSlot(i / 2, username, isReady);
                logger.info("set player " + (i / 2) + " to " + username + ", ready: " + isReady);
            }
        } else {
            logger.info("lobby joined failed, try again");
        }
    }
}
