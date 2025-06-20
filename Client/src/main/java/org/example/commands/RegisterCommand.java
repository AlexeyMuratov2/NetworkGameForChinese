package org.example.commands;

import org.example.controllers.ClientContext;
import org.example.view.LoginPanel;
import org.example.view.MainFrame;
import org.example.view.Panels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RegisterCommand implements Command{
    private static final Logger logger = Logger.getLogger(LoginCommand.class.getName());
    private final ClientContext clientContext;
    private final MainFrame mainFrame;

    @Autowired
    public RegisterCommand(ClientContext clientContext, MainFrame mainFrame) {
        this.clientContext = clientContext;
        this.mainFrame = mainFrame;
    }


    @Override
    public void execute(String args) {
        String[] parts = args.split(" ");
        String result = parts[0];
        String username = parts[1];
        if (result.equals("success")){
            clientContext.setUsername(username);
            mainFrame.switchTo(Panels.LOBBIES);
            logger.info("successfully register");
        }else{
            logger.info("registration failed, try again");
        }
    }
}
