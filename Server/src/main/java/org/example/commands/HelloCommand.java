package org.example.commands;

import org.springframework.stereotype.Component;

@Component
public class HelloCommand implements Command {
    @Override
    public String execute(String args) {
        return "Hello, client!";
    }
}
