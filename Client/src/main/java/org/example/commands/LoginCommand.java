package org.example.commands;

import org.example.view.LoginPanel;
import org.example.view.MainFrame;
import org.example.view.Panels;

import java.util.logging.Logger;

public class LoginCommand implements Command{
    private static final Logger logger = Logger.getLogger(LoginCommand.class.getName());
    @Override
    public void execute(String args){
        if (args.equals("success")){
            logger.info("successfully login");
        }else{
            LoginPanel.getInstance().showLoginFailedMessage();
            logger.info("login failed, try again");
        }
    }
}
