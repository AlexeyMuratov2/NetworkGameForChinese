package org.example.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientContext {
    private static ClientContext instance;
    private String username;

    private ClientContext(){}

    public ClientContext getInstance(){
        if (instance == null){
            synchronized (ClientContext.class){
                if (instance == null){
                    instance = new ClientContext();
                }
            }
        }
        return instance;
    }
}
