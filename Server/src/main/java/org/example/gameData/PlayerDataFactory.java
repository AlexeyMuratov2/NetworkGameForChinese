package org.example.gameData;

import org.springframework.stereotype.Component;

public class PlayerDataFactory {
    public static PlayerGameData getPlayerGameData(GamesName gameName, String username){
        switch (gameName){
            case GamesName.TranslationGame -> {
                return new TranslationGameUserData(username);
            }
            default -> throw new IllegalArgumentException("Unknown game: " + gameName);
        }
    }
}
