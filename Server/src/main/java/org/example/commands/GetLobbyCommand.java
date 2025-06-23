package org.example.commands;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.network.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class GetLobbyCommand implements Command{
    private SessionManager sessionManager;
    private static Logger logger = Logger.getLogger(GetLobbyCommand.class.getName());

    @Autowired
    public GetLobbyCommand(SessionManager sessionManager){
        this.sessionManager = sessionManager;
    }

    @Override
    public String execute(String args) {
        logger.info(args);
        return "";
    }
}
