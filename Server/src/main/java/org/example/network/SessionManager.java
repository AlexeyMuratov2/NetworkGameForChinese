package org.example.network;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class SessionManager {
    private final Map<Integer, GameSession> sessions = new ConcurrentHashMap<>();
    private final AtomicInteger sessionIdGenerator = new AtomicInteger(1);
    private static final Logger logger = Logger.getLogger(SessionManager.class.getName());

    public synchronized GameSession createSession() {
        GameSession session = new GameSession();
        int id = sessionIdGenerator.getAndIncrement();
        sessions.put(id, session);
        return session;
    }

    public void removeEmptySessions() {
        sessions.entrySet().removeIf(e -> e.getValue().isEmpty());
    }

    public void removeFromSessionByUsername(String username){
        for (GameSession session : sessions.values()){
            if (session.containsPlayer(username)){
                session.removePlayer(username);
            }
        }
        removeEmptySessions();
    }

    public Collection<GameSession> getAllSessions() {
        return sessions.values();

    }

    public List<String> getSessionDisplayNames() {
        return sessions.values().stream()
                .map(GameSession::getDisplayName)
                .collect(Collectors.toList());
    }

    public String getAllSessionName() {
        String sessionNames = String.join(" ", this.getSessionDisplayNames());
        logger.info("updateLobbiesList " + sessionNames);
        return sessionNames;
    }

    public GameSession getSessionByName(String username){
        logger.info("looking for user " + username +" in lobby");
        for(GameSession session : sessions.values()){
            if(session.containsPlayer(username)){
                return session;
            }
        }
        throw new NullPointerException("There are no such game session");
    }
}

