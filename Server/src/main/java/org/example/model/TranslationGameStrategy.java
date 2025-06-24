package org.example.model;

import org.example.network.GameSession;
import org.springframework.stereotype.Component;

@Component
public class TranslationGameStrategy implements GameStrategy{
    @Override
    public String getGameName() {
        return "";
    }

    @Override
    public void start(GameSession session) {

    }
}
