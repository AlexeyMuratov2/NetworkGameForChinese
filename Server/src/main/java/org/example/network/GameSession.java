package org.example.network;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameSession {
    @Getter
    private List<ClientHandler> clientsInSession = new ArrayList<>();
    @Setter
    @Getter
    private String name;

    public void addClientsInSession(ClientHandler clientHandler) {
        this.clientsInSession.add(clientHandler);
    }

    public void removeClientFromSession(ClientHandler clientHandler){
        this.clientsInSession.remove(clientHandler);
    }
}
