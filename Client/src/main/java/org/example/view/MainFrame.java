package org.example.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

@Component
public class MainFrame extends JFrame {
    private static final Logger logger = Logger.getLogger(MainFrame.class.getName());
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panelContainer = new JPanel(cardLayout);

    @Autowired
    private MainFrame(LoginPanel loginPanel,
                      RegisterPanel registerPanel,
                      LobbiesPanel lobbiesPanel,
                      GameLobbyPanel GameLobbyPanel){

        setTitle("Game view");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,900);
        setLocationRelativeTo(null);

        panelContainer.add(loginPanel, String.valueOf(Panels.LOGIN));
        panelContainer.add(registerPanel, String.valueOf(Panels.REGISTER));
        panelContainer.add(lobbiesPanel, String.valueOf(Panels.LOBBIES));
        panelContainer.add(GameLobbyPanel, String.valueOf(Panels.GAME_LOBBY));

        setContentPane(panelContainer);
        setVisible(true);
    }


    public void switchTo(Panels panelName){
        logger.info("switching to panel " + panelName.toString());
        cardLayout.show(panelContainer, String.valueOf(panelName));
    }
}
