package org.example.model;

import org.example.network.GameSession;
import org.springframework.stereotype.Component;

@Component
public class TranslationGameStrategy implements GameStrategy{
    private final String gameName = "TranslationGame";
    @Override
    public String getGameName() {
        return gameName;
    }

    @Override
    public void start(GameSession session) {
        session.sendMessageToSession(MessageFactory.startGame(gameName, session.getDisplayName()));
    }
}
