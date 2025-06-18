package org.example.controllers;

import org.example.view.LoginPanel;
import org.example.view.MainFrame;

public class LoginPanelController {
    private final MainFrame mainFrame = MainFrame.getInstance();
    private final LoginPanel loginPanel = LoginPanel.getInstance();
    private String username;
    private String password;
    public LoginPanelController(){
        loginPanel.getLoginButton().addActionListener(e -> OnLoginClick());
    }

    public void OnLoginClick(){
        this.username = String.valueOf(loginPanel.getLoginField());
        this.password = String.valueOf(loginPanel.getPassField());


    }
}
