package org.example.controllers;

import org.example.model.ClientConnectionHandler;
import org.example.model.ClientContext;
import org.example.model.HashUtils;
import org.example.model.MessageFactory;
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
    private final ClientContext clientContext;

    private String username;
    private String password;

    @Autowired
    public LoginPanelController(MainFrame mainFrame, LoginPanel loginPanel, ClientConnectionHandler clientConnectionHandler, ClientContext clientContext){
        this.mainFrame = mainFrame;
        this.loginPanel = loginPanel;
        this.clientConnectionHandler = clientConnectionHandler;
        this.clientContext = clientContext;

        loginPanel.getLoginButton().addActionListener(e -> onLoginClick());
        loginPanel.getSwitchToRegisterButton().addActionListener(e -> onRegisterClick());
    }

    private void onLoginClick(){
        this.username = loginPanel.getLoginField().getText();
        this.password = HashUtils.sha256(new String(loginPanel.getPassField().getPassword()));
        String msg = MessageFactory.login(username, password, clientContext.getPort());
        clientConnectionHandler.sendMessage(msg);
    }

    private void onRegisterClick(){
        mainFrame.switchTo(Panels.REGISTER);
    }
}
