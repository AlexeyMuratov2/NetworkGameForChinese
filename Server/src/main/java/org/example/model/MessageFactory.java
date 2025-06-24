package org.example.model;

import org.example.network.ClientHandler;
import org.springframework.stereotype.Component;

import java.util.List;

public class MessageFactory {

    public static String echo(String message) {
        return "Echo: " + message;
    }

    public static String login(boolean success, String username) {
        return (success ? "login success " : "login failed ") + username;
    }

    public static String register(boolean success, String username) {
        return (success ? "register success " : "register failed ") + username;
    }

    public static String lobbyJoined(boolean success, String sessionDisplayName) {
        return (success ? "lobbyJoined success " : "lobbyJoined failed ") + sessionDisplayName;
    }

    public static String startGame(String gameName) {
        return "StartGame " + gameName;
    }

    public static String updateLobbyInfo(List<ClientHandler> usersInLobby){
        StringBuilder msg = new StringBuilder("updateLobbyInfo (");
        for (ClientHandler client : usersInLobby){
            msg.append(client.getUsername());
            msg.append(",");
            msg.append(client.isReady());
            msg.append(",");
        }
        msg.append(")");
        return msg.toString();
    }

    public static String updateLobbiesList(String sessionNames) {
        return "updateLobbiesList " + sessionNames;
    }
}

