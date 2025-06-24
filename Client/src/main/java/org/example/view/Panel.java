package org.example.view;

import org.springframework.stereotype.Component;

@Component
public interface Panel {
    String getPanelName();
    java.awt.Component asComponent();
}
