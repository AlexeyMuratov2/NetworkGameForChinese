package org.example.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;
import java.util.List;

@Component
public class MainFrame extends JFrame {
    private static final Logger logger = Logger.getLogger(MainFrame.class.getName());
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panelContainer = new JPanel(cardLayout);

    @Autowired
    public MainFrame(List<Panel> panelList) {

        setTitle("Game view");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280,900);
        setLocationRelativeTo(null);

        for(Panel panel : panelList) {
            panelContainer.add(panel.asComponent(), String.valueOf(panel.getPanelName()));
        }

        setContentPane(panelContainer);
        setVisible(true);
    }


    public void switchTo(Panels panelName){
        logger.info("switching to panel " + panelName.toString());
        cardLayout.show(panelContainer, String.valueOf(panelName));
    }
}
