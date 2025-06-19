package org.example.controllers;

import org.example.model.ClientConnectionHandler;
import org.example.model.HashUtils;
import org.example.view.LoginPanel;
import org.example.view.MainFrame;
import org.example.view.Panels;

public class LoginPanelController {
    private final MainFrame mainFrame = MainFrame.getInstance();
    private final LoginPanel loginPanel = LoginPanel.getInstance();

    private String username;
    private String password;
    public LoginPanelController(){
        loginPanel.getLoginButton().addActionListener(e -> onLoginClick());
        loginPanel.getSwitchToRegisterButton().addActionListener(e -> onRegisterClick());
    }

    private void onLoginClick(){
        this.username = loginPanel.getLoginField().getText();
        this.password = HashUtils.sha256(new String(loginPanel.getPassField().getPassword()));
        ClientConnectionHandler.getInstance().sendMessage("login " + username + " " + password);
    }

    private void onRegisterClick(){
        MainFrame.getInstance().switchTo(Panels.REGISTER);
    }
}
