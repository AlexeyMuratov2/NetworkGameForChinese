package org.example.controllers;

import org.example.model.ClientConnectionHandler;
import org.example.view.LobbiesPanel;
import org.example.view.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@Component
public class LobbyPanelController {
    private final MainFrame mainFrame;
    private final LobbiesPanel lobbiesPanel;
    private final ClientConnectionHandler clientConnectionHandler;

    @Autowired
    public LobbyPanelController(MainFrame mainFrame, LobbiesPanel lobbiesPanel, ClientConnectionHandler clientConnectionHandler){
        this.mainFrame = mainFrame;
        this.lobbiesPanel = lobbiesPanel;
        this.clientConnectionHandler = clientConnectionHandler;

        lobbiesPanel.getLobbyList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    String name = lobbiesPanel.getLobbyList().getSelectedValue();
                    if (name != null){
                        String msg = "getLobby " + name;
                        clientConnectionHandler.sendMessage(msg);
                    }
                }
            }
        });
    }


}
