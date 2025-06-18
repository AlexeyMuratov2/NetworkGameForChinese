package org.example.commands;

import org.example.ClientConnectionHandler;

import java.util.logging.Logger;

public class HelloCommand implements Command {
    private static final Logger logger = Logger.getLogger(ClientConnectionHandler.class.getName());
    @Override
    public void execute(String args) {
        logger.info("hello! I'm a bot.");
    }
}
