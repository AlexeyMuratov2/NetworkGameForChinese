package org.example.gameData;

import lombok.Getter;
import lombok.Setter;

public class TranslationGameUserData implements PlayerGameData{
    private final String username;
    @Getter @Setter
    private int score = 0;
    @Getter @Setter
    private int time = 0;

    public TranslationGameUserData(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void addScore(int score){
        this.score += score;
    }
}
