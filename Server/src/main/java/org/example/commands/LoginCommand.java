package org.example.commands;

import org.example.database.LoginRepository;
import org.example.network.ClientManager;
import org.example.network.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginCommand implements Command{

    private final LoginRepository loginRepository;
    private final ClientManager clientManager;

    @Autowired
    public LoginCommand(LoginRepository loginRepository, ClientManager clientManager){
        this.loginRepository = loginRepository;
        this.clientManager = clientManager;
    }

    @Override
    public String execute(String args) {
        String[] parts = args.split(" ");
        String username = parts[0];
        String hashPass = parts[1];
        boolean isLogin = loginRepository.isLoginSuccess(username, hashPass);
        return isLogin ? "login success " + username : "login failed " + username;
    }
}
