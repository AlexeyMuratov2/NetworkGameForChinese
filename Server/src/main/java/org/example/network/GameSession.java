package org.example.network;

import lombok.Getter;
import lombok.Setter;
import org.example.model.ApplicationContextHolder;
import org.example.model.GameStrategy;
import org.example.model.GameStrategyFactory;
import org.example.model.MessageFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GameSession {
    private final Set<String> players = ConcurrentHashMap.newKeySet();
    private ClientManager clientManager = ApplicationContextHolder.getBean(ClientManager.class);
    private GameStrategyFactory gameStrategyFactory = ApplicationContextHolder.getBean(GameStrategyFactory.class);

    public void addPlayer(String username) {
        players.add(username);
    }

    public void removePlayer(String username) {
        players.remove(username);
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public boolean containsPlayer(String username) {
        return players.contains(username);
    }

    public Set<String> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public String getDisplayName() {
        return String.join(",", players);
    }

    public void updateLobbyInfoForAllUsers() {
        String msg = MessageFactory.updateLobbyInfo(this.getDisplayName());
        for (String username : players) {
            clientManager.getClientHandlerByName(username).sendMessage(msg);
        }
    }

    public void startRandomGame() {
        GameStrategy game = gameStrategyFactory.getRandomGame();
        game.start(this);
        String msg = MessageFactory.startGame(game.getGameName());
        this.sendMessageToSession(msg);
    }

    public void sendMessageToSession(String msg) {
        for (String username : this.players) {
            clientManager.getClientHandlerByName(username).sendMessage(msg);
        }
    }

    public boolean isReadyToPlay() {
        if (this.players.size() <= 1){
            return false;
        }
        for (String username : this.players) {
            ClientHandler clientHandler = clientManager.getClientHandlerByName(username);
            if (!clientHandler.isReady()) {
                return false;
            }
        }
        return true;
    }
}
