package org.example.network;

import org.example.model.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class MainGameManager {
    private final ClientManager clientManager;
    private final SessionManager sessionManager;
    private static Logger logger = Logger.getLogger(MainGameManager.class.getName());

    @Autowired
    public MainGameManager(ClientManager clientManager, SessionManager sessionManager) {
        this.clientManager = clientManager;
        this.sessionManager = sessionManager;
    }

    public void onClientDisconnect(ClientHandler clientHandler){
        try {
            GameSession session = sessionManager.getSessionByName(clientHandler.getUsername());

            if (session == null) {
                logger.warning("No session found for user: " + clientHandler.getUsername());
                return;
            }

            String currentUsername = clientHandler.getUsername();
            String playersInLobby = session.getDisplayName();

            String cleanPlayers =
                    java.util.Arrays.stream(playersInLobby.split(","))
                            .filter(name -> !name.equals(currentUsername))
                            .collect(Collectors.joining(","));

            session.sendMessageToSession(MessageFactory.updateLobbyInfo(cleanPlayers));

        } catch (Exception e) {
            logger.severe("Error while updating lobby info: " + e.getMessage());
            e.printStackTrace();
        }
        clientManager.removeClient(clientHandler.getPORT());
        sessionManager.removeFromSessionByUsername(clientHandler.getUsername());
    }
}
