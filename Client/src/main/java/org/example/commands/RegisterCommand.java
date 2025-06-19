package org.example.commands;

public class RegisterCommand implements Command{
    @Override
    public void execute(String args) {
        if(args.equals("success")){
            System.out.println("register success");
        }
        else {
            System.out.println("an error while creating account");
        }
    }
}
