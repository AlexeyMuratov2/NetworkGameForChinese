package org.example.controllers;

import org.example.model.ClientConnectionHandler;
import org.example.model.HashUtils;
import org.example.view.LoginPanel;
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

    private String username;
    private String passwd;
    private String passwdConfirm;

    @Autowired
    public RegisterPanelController(MainFrame mainFrame, RegisterPanel registerPanel, ClientConnectionHandler clientConnectionHandler){
        this.mainFrame = mainFrame;
        this.registerPanel = registerPanel;
        this.clientConnectionHandler = clientConnectionHandler;

        registerPanel.getRegisterButton().addActionListener(e -> onRegisterClick());
        registerPanel.getSwitchToLoginButton().addActionListener(e -> onReturnButtonClick());
    }

    private void onRegisterClick(){
        this.username = registerPanel.getUsernameField().getText();
        this.passwd = new String(registerPanel.getPasswordField().getPassword());
        this.passwdConfirm = new String(registerPanel.getConfirmPasswordField().getPassword());

        if (passwd.equals(passwdConfirm)){
            String hashPass = HashUtils.sha256(passwd);
            clientConnectionHandler.sendMessage("register " + username + " " + hashPass);
        }else {
            registerPanel.showPasswordMismatchMessage();
        }
    }

    private void onReturnButtonClick(){
        mainFrame.switchTo(Panels.LOGIN);
    }
}
