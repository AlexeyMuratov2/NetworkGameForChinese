package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientManager {
    private static final Map<Integer, ClientHandler> clients = new ConcurrentHashMap<>();

    public static void addClient(Integer port, ClientHandler clientHandler) {
        clients.put(port, clientHandler);
    }

    public static ClientHandler getClient(Integer port) {
        return clients.get(port);
    }

    public static void removeClient(Integer port) {
        clients.remove(port);
    }

    public static void removeClient(ClientHandler clientHandler) {
        for (Map.Entry<Integer, ClientHandler> entry : clients.entrySet()) {
            if (entry.getValue() == clientHandler) {
                clients.remove(entry.getKey());
                break;
            }
        }
    }
}
