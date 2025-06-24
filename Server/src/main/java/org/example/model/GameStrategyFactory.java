package org.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Component
public class GameStrategyFactory {
    private HashMap<String, GameStrategy> gameStrategies = new HashMap<>();

    @Autowired
    public GameStrategyFactory(List<GameStrategy> strategies) {
        for(GameStrategy strategy : strategies){
            this.gameStrategies.put(strategy.getGameName(), strategy);
        }
    }

    public GameStrategy getRandomGame(){
        List<GameStrategy> list = new ArrayList<>(gameStrategies.values());
        return list.get(new Random().nextInt(list.size()));
    }

    public GameStrategy getGameByName(String gameName){
        return gameStrategies.get(gameName);
    }
}
