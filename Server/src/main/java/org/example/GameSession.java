package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameSession {
    private Map<String, ClientHandler> clientsInSession = new ConcurrentHashMap<>();
}
