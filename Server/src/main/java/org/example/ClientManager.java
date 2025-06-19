package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientManager {
    private static final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public static void addClient(String username, ClientHandler clientHandler) {
        clients.put(username, clientHandler);
    }

    public static ClientHandler getClient(Integer port) {
        return clients.get(port);
    }

    public static void removeClient(String username) {
        clients.remove(username);
    }

    public static void removeClient(ClientHandler clientHandler) {
        for (Map.Entry<String, ClientHandler> entry : clients.entrySet()) {
            if (entry.getValue() == clientHandler) {
                clients.remove(entry.getKey());
                break;
            }
        }
    }
}
