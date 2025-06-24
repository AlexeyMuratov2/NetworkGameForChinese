package org.example.commands;

public interface Command {
    String getCommandName();
    String execute(String args);
}
