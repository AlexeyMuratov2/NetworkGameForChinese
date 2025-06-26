package org.example.gameData;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TranslationGameUserData implements PlayerGameData{
    private final String username;
    @Getter @Setter
    private int score = 0;
    private Long timer_start;
    private Long timer_end;
    @Getter @Setter
    private int move = 0;

    public TranslationGameUserData(String username) {
        this.username = username;

        this.timer_start = System.currentTimeMillis();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isGameFinished() {
        return this.move >= 5;
    }

    public void addScore(int score){
        this.score += score;
    }

    public Long getTime(){
        if (this.timer_end == null){
            this.timer_end = System.currentTimeMillis();
        }
        return this.timer_end-this.timer_start;
    }
}
