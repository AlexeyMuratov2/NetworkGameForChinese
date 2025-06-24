package org.example.controllers;

import org.example.model.ClientConnectionHandler;
import org.example.model.ClientContext;
import org.example.model.HashUtils;
import org.example.model.MessageFactory;
import org.example.view.MainFrame;
import org.example.view.Panels;
import org.example.view.RegisterPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterPanelController {
    private final MainFrame mainFrame;
    private final RegisterPanel registerPanel;
    private final ClientConnectionHandler clientConnectionHandler;
    private final ClientContext clientContext;

    private String username;
    private String passwd;
    private String passwdConfirm;

    @Autowired
    public RegisterPanelController(MainFrame mainFrame, RegisterPanel registerPanel, ClientConnectionHandler clientConnectionHandler, ClientContext clientContext){
        this.mainFrame = mainFrame;
        this.registerPanel = registerPanel;
        this.clientConnectionHandler = clientConnectionHandler;
        this.clientContext = clientContext;

        registerPanel.getRegisterButton().addActionListener(e -> onRegisterClick());
        registerPanel.getSwitchToLoginButton().addActionListener(e -> onReturnButtonClick());
    }

    private void onRegisterClick(){
        this.username = registerPanel.getUsernameField().getText();
        this.passwd = new String(registerPanel.getPasswordField().getPassword());
        this.passwdConfirm = new String(registerPanel.getConfirmPasswordField().getPassword());

        if (passwd.equals(passwdConfirm)){
            String hashPass = HashUtils.sha256(passwd);
            String msg = MessageFactory.register(username, hashPass, clientContext.getPort());
            clientConnectionHandler.sendMessage(msg);
        }else {
            registerPanel.showPasswordMismatchMessage();
        }
    }

    private void onReturnButtonClick(){
        mainFrame.switchTo(Panels.LOGIN);
    }
}
