package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panelContainer = new JPanel(cardLayout);
    private final JPanel LoginPanel = new LoginPanel();

    public MainFrame(){
        setTitle("Game view");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,900);
        setLocationRelativeTo(null);

        panelContainer.add(LoginPanel, String.valueOf(Panels.LOGIN));

        setContentPane(panelContainer);
        setVisible(true);
    }

    public void switchTo(Panels panelName){
        cardLayout.show(panelContainer, String.valueOf(panelName));
    }
}
