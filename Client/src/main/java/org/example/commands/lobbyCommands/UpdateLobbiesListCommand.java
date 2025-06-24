package org.example.commands.lobbyCommands;

import org.example.commands.Command;
import org.example.view.LobbiesPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateLobbiesListCommand implements Command {
    @Autowired
    private LobbiesPanel lobbiesPanel;

    @Override
    public String getName() {
        return "updateLobbiesList";
    }

    @Override
    public void execute(String args) {
        lobbiesPanel.setLobbyList(args);
    }
}
