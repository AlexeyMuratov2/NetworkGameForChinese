package org.example.commands;

import org.example.model.MessageFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloCommand implements Command {
    @Override
    public String execute(String args) {
        return MessageFactory.echo(args);
    }

    @Override
    public String getCommandName(){
        return "hello";
    }
}
