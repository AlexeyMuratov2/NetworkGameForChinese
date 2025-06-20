package org.example.network;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClientManager {
    private final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public void addClient(String username, ClientHandler clientHandler) {
        clients.put(username, clientHandler);
    }
    public ClientHandler getClient(Integer port) {
        return clients.get(port);
    }
    public void removeClient(String username) {
        clients.remove(username);
    }

    public void removeClient(ClientHandler clientHandler) {
        for (Map.Entry<String, ClientHandler> entry : clients.entrySet()) {
            if (entry.getValue() == clientHandler) {
                clients.remove(entry.getKey());
                break;
            }
        }
    }
}
