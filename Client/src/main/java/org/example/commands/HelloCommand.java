package org.example.commands;

import org.example.model.ClientConnectionHandler;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class HelloCommand implements Command {
    private static final Logger logger = Logger.getLogger(ClientConnectionHandler.class.getName());

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public void execute(String args) {
        logger.info("hello! I'm a bot.");
    }
}
