package org.example.model;

public class MessageFactory {
    public static String login(String username, String password, int port) {
        return "login " + username + " " + password + " " + port;
    }

    public static String register(String username, String hashedPassword, int port) {
        return "register " + username + " " + hashedPassword + " " + port;
    }

    public static String joinLobby(String username, String lobbyName) {
        return "joinLobby " + username + " " + lobbyName;
    }

    public static String updateLobbies(String username) {
        return "updateLobbies " + username;
    }

    public static String createLobby(String username) {
        return "createLobby " + username;
    }
}
