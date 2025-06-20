package org.example.commands;

import org.example.model.ClientConnectionHandler;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EchoCommand implements Command {
    private static final Logger logger = Logger.getLogger(ClientConnectionHandler.class.getName());
    @Override
    public void execute(String args) {
        logger.info(args);
    }
}
