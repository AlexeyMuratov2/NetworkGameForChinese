package org.example.network;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    public String sisi = "сиська";
    private Map<String, GameSession> gameSessions = new ConcurrentHashMap<>();

    public Map<String, GameSession> getGameSessions() {
        return gameSessions;
    }

    public void addGameSession(GameSession gameSession) {
        this.gameSessions.put(gameSession.getName(), gameSession);
    }
}
