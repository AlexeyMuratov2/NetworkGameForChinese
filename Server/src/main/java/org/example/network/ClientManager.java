package org.example.network;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClientManager {
    private final Map<Integer, ClientHandler> clients = new ConcurrentHashMap<>();

    private ClientManager(){}


    public void addClient(Integer port, ClientHandler clientHandler) {
        clients.put(port, clientHandler);
    }
    public ClientHandler getClient(Integer port) {
        return clients.get(port);
    }

    public ClientHandler getClientHandlerByName(String username){
        return clients.values().stream()
                .filter(handler -> username.equals(handler.getUsername()))
                .findFirst()
                .orElse(null);
    }

    public void removeClient(Integer port) {
        clients.remove(port);
    }

    public void removeClient(ClientHandler clientHandler) {
        for (Map.Entry<Integer, ClientHandler> entry : clients.entrySet()) {
            if (entry.getValue() == clientHandler) {
                clients.remove(entry.getKey());
                break;
            }
        }
    }
}
