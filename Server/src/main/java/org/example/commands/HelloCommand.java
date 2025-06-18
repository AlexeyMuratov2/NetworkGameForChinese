package org.example.commands;

public class HelloCommand implements Command {
    @Override
    public String execute(String args) {
        return "Hello, client!";
    }
}
