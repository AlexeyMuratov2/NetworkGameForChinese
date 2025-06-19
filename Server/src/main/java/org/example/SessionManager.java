package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private Map<Integer, GameSession> gameSessions = new ConcurrentHashMap<>();
}
