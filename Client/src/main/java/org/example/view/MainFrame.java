package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class MainFrame extends JFrame {
    private static final Logger logger = Logger.getLogger(MainFrame.class.getName());
    private static MainFrame instance;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panelContainer = new JPanel(cardLayout);
    private final JPanel loginPanel = LoginPanel.getInstance();
    private final JPanel registerPanel = RegisterPanel.getInstance();

    private MainFrame(){
        setTitle("Game view");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,900);
        setLocationRelativeTo(null);

        panelContainer.add(loginPanel, String.valueOf(Panels.LOGIN));
        panelContainer.add(registerPanel, String.valueOf(Panels.REGISTER));

        setContentPane(panelContainer);
        setVisible(true);
    }

    public static MainFrame getInstance(){
        if (instance == null){
            synchronized (MainFrame.class){
                if (instance == null){
                    instance = new MainFrame();
                }
            }
        }
        return instance;
    }

    public void switchTo(Panels panelName){
        logger.info("switching to panel " + panelName.toString());
        cardLayout.show(panelContainer, String.valueOf(panelName));
    }
}
