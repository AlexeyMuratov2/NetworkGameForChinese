package org.example.commands;

import org.example.database.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginCommand implements Command{
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public String execute(String args) {
        String[] parts = args.split(" ");
        boolean isLogin = loginRepository.isLoginSuccess(parts[0], parts[1]);
        return isLogin ? "login success " + parts[0] : "login failed " + parts[0];
    }
}
