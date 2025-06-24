package org.example.commands;

import org.example.database.RegisterRepository;
import org.example.model.MessageFactory;
import org.example.network.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterCommand implements Command{
    private RegisterRepository registerRepository;
    private ClientManager clientManager;

    @Autowired
    public RegisterCommand(RegisterRepository registerRepository, ClientManager clientManager){
        this.registerRepository = registerRepository;
        this.clientManager = clientManager;
    }

    @Override
    public String execute(String args) {
        String[] parts = args.split(" ");
        String username = parts[0];
        String passwdHash = parts[1];
        String port = parts[2];
        clientManager.getClient(Integer.parseInt(port)).setUsername(username);
        boolean success = registerRepository.register(username, passwdHash);
        return MessageFactory.register(success, username);
    }
}
