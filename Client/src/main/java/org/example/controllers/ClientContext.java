package org.example.controllers;

import lombok.Getter;
import lombok.Setter;
import org.example.view.LobbiesPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ClientContext {
    private String username;
    private final LobbiesPanel lobbiesPanel;

    @Autowired
    public ClientContext(@Lazy LobbiesPanel lobbiesPanel){
        this.lobbiesPanel = lobbiesPanel;
    }

    public void setUsername(String username) {
        this.username = username;
        lobbiesPanel.refreshUsername();
    }
}
