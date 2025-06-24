package org.example.model;

import org.example.network.GameSession;

public interface GameStrategy {
    String getGameName();
    void start(GameSession session);
}