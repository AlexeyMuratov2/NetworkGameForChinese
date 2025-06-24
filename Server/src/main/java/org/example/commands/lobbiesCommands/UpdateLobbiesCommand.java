package org.example.commands.lobbiesCommands;

import org.example.commands.Command;
import org.example.model.MessageFactory;
import org.example.network.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateLobbiesCommand implements Command {
    @Autowired
    private SessionManager sessionManager;

    @Override
    public String execute(String args) {
        return MessageFactory.updateLobbiesList(sessionManager.getAllSessionName());
    }

    @Override
    public String getCommandName(){
        return "updateLobbies";
    }
}
