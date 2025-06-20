package org.example.commands;

import org.example.database.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterCommand implements Command{
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public String execute(String args) {
        String[] parts = args.split(" ");
        String username = parts[0];
        String passwdHash = parts[1];
        if (registerRepository.register(username, passwdHash)) {
            return "register success " + username;
        }else{
            return "register failed " + username;
        }
    }
}
