package org.example.commands.lobbyCommands;

import org.example.commands.Command;
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
    public String getName() {
        return "updateLobbyInfo";
    }

    @Override
    public void execute(String args) {
        logger.info("Received lobby update args: " + args);

        if (args.startsWith("(") && args.endsWith(")")) {
            args = args.substring(1, args.length() - 1);
        }

        String[] parts = args.split(",");
        gameLobbyPanel.clearAllSlots();

        for (int i = 0; i < parts.length - 1; i += 2) {
            try {
                String username = parts[i].trim();
                String readyFlag = parts[i + 1].trim();
                boolean isReady = Boolean.parseBoolean(readyFlag);

                gameLobbyPanel.updatePlayerSlot(i / 2, username, isReady);
                logger.info("Updated slot " + (i / 2) + " → " + username + " (ready=" + isReady + ")");

            } catch (Exception e) {
                logger.warning("Failed to parse player at index " + i + ": " + e.getMessage());
            }
        }
    }
}
