package org.example.commands;

import java.util.Optional;


public class GameCommandParser {

    private GameCommandParser() {
        // закрываем конструктор — утилитарный класс
    }

    /** Возвращает имя игры из команды */
    public static String getGameName(String message) {
        String[] parts = message.split(" ", 3);
        return parts.length > 0 ? parts[0] : "";
    }

    /** Возвращает строку игроков */
    public static String getPlayers(String message) {
        String[] parts = message.split(" ", 3);
        return parts.length > 1 ? parts[1] : "";
    }

    /** Возвращает аргументы как есть (может быть со скобками) */
    public static String getRawArgs(String message) {
        String[] parts = message.split(" ", 3);
        return parts.length > 2 ? parts[2].trim() : "";
    }

    /** Возвращает аргументы без скобок, если они есть */
    public static String getArgsStripped(String message) {
        String rawArgs = getRawArgs(message);
        if (rawArgs.startsWith("(") && rawArgs.endsWith(")")) {
            return rawArgs.substring(1, rawArgs.length() - 1).trim();
        }
        return rawArgs;
    }

    /** Возвращает аргументы как массив строк, разбитый по запятой */
    public static String[] getArgsArray(String message) {
        return getArgsStripped(message).split(",", -1);
    }

    /** Безопасно достаёт аргумент по индексу */
    public static Optional<String> getArg(String message, int index) {
        String[] args = getArgsArray(message);
        if (index >= 0 && index < args.length) {
            return Optional.of(args[index].trim());
        }
        return Optional.empty();
    }

    /** Возвращает содержимое ПЕРВЫХ внешних круглых скобок (без них) */
    public static Optional<String> extractFirstBracketContent(String message) {
        int start = message.indexOf('(');
        int end = message.indexOf(')', start + 1);

        if (start != -1 && end != -1 && end > start) {
            return Optional.of(message.substring(start + 1, end).trim());
        }
        return Optional.empty();
    }
}
