package org.example.commands;

import org.springframework.stereotype.Component;

@Component
public class EchoCommand implements Command{
    @Override
    public String execute(String args) {
        return "Echo: " + args;
    }
}
