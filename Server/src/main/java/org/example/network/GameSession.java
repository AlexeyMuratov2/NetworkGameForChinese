package org.example.network;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GameSession {
    private final Set<String> players = ConcurrentHashMap.newKeySet();

    public void addPlayer(String username) {
        players.add(username);
    }

    public void removePlayer(String username) {
        players.remove(username);
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public Set<String> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public String getDisplayName() {
        return String.join(",", players);
    }
}
