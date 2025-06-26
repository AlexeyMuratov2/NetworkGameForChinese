package org.example.gameData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Component
public class GameDataFactory {
    private Map<GamesName, Map<String, PlayerGameData>> playerDataStorage = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends PlayerGameData> T getOrCreateGameData(GamesName gameName, String username, Class<T> clazz) {
        PlayerGameData data = playerDataStorage
                .computeIfAbsent(gameName, g -> new ConcurrentHashMap<>())
                .computeIfAbsent(username, u -> PlayerDataFactory.getPlayerGameData(gameName, u));

        if (!clazz.isInstance(data)) {
            throw new IllegalStateException("Invalid data type for game " + gameName);
        }
        return clazz.cast(data);
    }

}
