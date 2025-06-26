package org.example.model;

import org.example.network.GameSession;

public interface GameStrategy {
    String getGameName();
    void start(GameSession session);
    String getOnMoveMessage(String userName, int move, String args);
}