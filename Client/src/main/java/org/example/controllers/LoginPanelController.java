package org.example.controllers;

import org.example.model.ClientConnectionHandler;
import org.example.model.HashUtils;
import org.example.view.LoginPanel;
import org.example.view.MainFrame;
import org.example.view.Panels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPanelController {
    private final MainFrame mainFrame;
    private final LoginPanel loginPanel;
    private final ClientConnectionHandler clientConnectionHandler;

    private String username;
    private String password;

    @Autowired
    public LoginPanelController(MainFrame mainFrame, LoginPanel loginPanel, ClientConnectionHandler clientConnectionHandler){
        this.mainFrame = mainFrame;
        this.loginPanel = loginPanel;
        this.clientConnectionHandler = clientConnectionHandler;

        loginPanel.getLoginButton().addActionListener(e -> onLoginClick());
        loginPanel.getSwitchToRegisterButton().addActionListener(e -> onRegisterClick());
    }

    private void onLoginClick(){
        this.username = loginPanel.getLoginField().getText();
        this.password = HashUtils.sha256(new String(loginPanel.getPassField().getPassword()));
        clientConnectionHandler.sendMessage("login " + username + " " + password);
    }

    private void onRegisterClick(){
        mainFrame.switchTo(Panels.REGISTER);
    }
}
