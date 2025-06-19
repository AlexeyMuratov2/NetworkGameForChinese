package org.example.commands;

import org.example.SpringContext;
import org.example.database.RegisterRepository;

public class RegisterCommand implements Command{
    @Override
    public String execute(String args) {
        String[] parts = args.split(" ");
        String username = parts[0];
        String passwdHash = parts[1];
        if (SpringContext.getBean(RegisterRepository.class).register(username, passwdHash)) {
            return "register success";
        }else{
            return "register failed";
        }
    }
}
