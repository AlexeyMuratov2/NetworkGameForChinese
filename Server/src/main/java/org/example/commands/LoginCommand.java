package org.example.commands;

import org.example.SpringContext;
import org.example.database.LoginRepository;

public class LoginCommand implements Command{
    private LoginRepository loginRepository;

    public LoginCommand(){
        loginRepository = SpringContext.getBean(LoginRepository.class);
    }

    @Override
    public String execute(String args) {
        String[] parts = args.split(" ");
        boolean isLogin = loginRepository.isLoginSuccess(parts[0], parts[1]);
        return isLogin ? "login success" : "login failed";
    }
}
