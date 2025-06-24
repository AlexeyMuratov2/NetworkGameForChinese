package org.example.view;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Getter
@Component
public class GameLobbyPanel extends JPanel {

    private static final int MAX_PLAYERS = 4;

    private final JLabel[] playerLabels = new JLabel[MAX_PLAYERS];
    private final boolean[] readyFlags = new boolean[MAX_PLAYERS];

    private final JButton readyButton = new JButton("✅ Ready");
    private final JButton leaveButton = new JButton("🚪 Leave Lobby");

    private BufferedImage backgroundImage;

    public GameLobbyPanel() {
        setPreferredSize(new Dimension(1280, 900));
        setLayout(new BorderLayout());

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/background.jpg")));
        } catch (IOException | NullPointerException e) {
            System.err.println("Failed to load background: " + e.getMessage());
        }

        setupUI();
    }

    private void setupUI() {
        // ===== Title =====
        JLabel titleLabel = new JLabel("Lobby - Waiting for Players", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setForeground(new Color(178, 34, 34));
        titleLabel.setBorder(new EmptyBorder(50, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);

        // ===== Center: player slots =====
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridLayout(2, 2, 40, 40)); // 2x2 layout

        Font playerFont = new Font("SansSerif", Font.BOLD, 28);
        Color slotColor = new Color(255, 255, 255, 220);

        for (int i = 0; i < MAX_PLAYERS; i++) {
            JLabel slot = new JLabel("🧍 Waiting...", SwingConstants.CENTER);
            slot.setOpaque(true);
            slot.setBackground(slotColor);
            slot.setForeground(Color.BLACK);
            slot.setFont(playerFont);
            slot.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            playerLabels[i] = slot;
            readyFlags[i] = false;
            centerPanel.add(slot);
        }

        centerPanel.setBorder(new EmptyBorder(20, 200, 20, 200));
        add(centerPanel, BorderLayout.CENTER);

        // ===== Bottom: control buttons =====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        bottomPanel.setOpaque(false);

        readyButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        readyButton.setBackground(new Color(60, 179, 113));
        readyButton.setForeground(Color.WHITE);
        readyButton.setFocusPainted(false);
        readyButton.setPreferredSize(new Dimension(220, 60));

        leaveButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        leaveButton.setBackground(new Color(178, 34, 34));
        leaveButton.setForeground(Color.WHITE);
        leaveButton.setFocusPainted(false);
        leaveButton.setPreferredSize(new Dimension(220, 60));

        bottomPanel.add(readyButton);
        bottomPanel.add(leaveButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // ✅ Обновление слота игрока с флагом готовности
    public void updatePlayerSlot(int index, String playerName, boolean isReady) {
        if (index >= 0 && index < MAX_PLAYERS) {
            readyFlags[index] = isReady;
            String status = isReady ? " ✅ Ready" : " ❌ Not ready";
            playerLabels[index].setText("🎮 " + playerName + status);
        }
    }

    // ✅ Обновление только флага готовности
    public void setPlayerReady(int index, boolean isReady) {
        if (index >= 0 && index < MAX_PLAYERS) {
            String currentText = playerLabels[index].getText();
            if (!currentText.contains("🎮")) return; // слот пуст
            String name = currentText.replaceAll("🎮 ", "").replaceAll(" ✅ Ready", "").replaceAll(" ❌ Not ready", "");
            updatePlayerSlot(index, name, isReady);
        }
    }

    // ❌ Очистка одного слота
    public void clearPlayerSlot(int index) {
        if (index >= 0 && index < MAX_PLAYERS) {
            playerLabels[index].setText("🧍 Waiting...");
            readyFlags[index] = false;
        }
    }

    // ❌ Очистка всех слотов
    public void clearAllSlots() {
        for (int i = 0; i < MAX_PLAYERS; i++) {
            clearPlayerSlot(i);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
