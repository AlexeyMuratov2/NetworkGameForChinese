package org.example.commands;

public class EchoCommand implements Command{
    @Override
    public String execute(String args) {
        return "Echo: " + args;
    }
}
