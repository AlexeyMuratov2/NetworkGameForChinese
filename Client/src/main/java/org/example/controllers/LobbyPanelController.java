package org.example.controllers;

import org.example.model.ClientConnectionHandler;
import org.example.model.ClientContext;
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
    private final ClientContext clientContext;

    @Autowired
    public LobbyPanelController(MainFrame mainFrame,
                                LobbiesPanel lobbiesPanel,
                                ClientConnectionHandler clientConnectionHandler,
                                ClientContext clientContext){
        this.mainFrame = mainFrame;
        this.lobbiesPanel = lobbiesPanel;
        this.clientConnectionHandler = clientConnectionHandler;
        this.clientContext = clientContext;

        lobbiesPanel.getLobbyList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    String name = lobbiesPanel.getLobbyList().getSelectedValue();
                    if (name != null){
                        String msg = "joinLobby " + name;
                        clientConnectionHandler.sendMessage(msg);
                    }
                }
            }
        });

        lobbiesPanel.getRefreshButton().addActionListener(e -> updateLobbies());
        lobbiesPanel.getCreateLobbyButton().addActionListener(e -> createLobby());
    }

    public void updateLobbies() {
        String msg = "updateLobbies " + clientContext.getUsername();
        clientConnectionHandler.sendMessage(msg);
    }

    public void createLobby() {
        String msg = "createLobby " + clientContext.getUsername();
        clientConnectionHandler.sendMessage(msg);
    }


}
