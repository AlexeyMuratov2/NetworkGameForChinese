package org.example.commands.clientRegistrationCommands;

import org.example.commands.Command;
import org.example.database.LoginRepository;
import org.example.model.MessageFactory;
import org.example.network.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginCommand implements Command {

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
        String port = parts[2];
        clientManager.getClient(Integer.parseInt(port)).setUsername(username);
        boolean isLogin = loginRepository.isLoginSuccess(username, hashPass);
        return MessageFactory.login(isLogin, username);
    }

    @Override
    public String getCommandName(){
        return "login";
    }
}
