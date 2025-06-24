package org.example.model.games;

public interface GameStrategy {
    String getGameName();
    void start(String playersInSession);
}
