package org.example.view;

import lombok.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Getter
@org.springframework.stereotype.Component
public class GameLobbyPanel extends JPanel {

    private final JLabel[] playerLabels = new JLabel[4];
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

        // ===== Title =====
        JLabel titleLabel = new JLabel("Lobby - Waiting for Players", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setForeground(new Color(178, 34, 34));
        titleLabel.setBorder(new EmptyBorder(50, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);

        // ===== Center: player slots =====
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridLayout(2, 2, 40, 40)); // 2x2 players

        Font playerFont = new Font("SansSerif", Font.BOLD, 28);
        Color textColor = Color.BLACK;
        Color slotColor = new Color(255, 255, 255, 220);

        for (int i = 0; i < 4; i++) {
            JLabel slot = new JLabel("🧍 Waiting...", SwingConstants.CENTER);
            slot.setOpaque(true);
            slot.setBackground(slotColor);
            slot.setForeground(textColor);
            slot.setFont(playerFont);
            slot.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            playerLabels[i] = slot;
            centerPanel.add(slot);
        }

        centerPanel.setBorder(new EmptyBorder(20, 200, 20, 200));
        add(centerPanel, BorderLayout.CENTER);

        // ===== Bottom: buttons =====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        bottomPanel.setOpaque(false);

        readyButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        readyButton.setBackground(new Color(60, 179, 113));
        readyButton.setForeground(Color.WHITE);
        readyButton.setFocusPainted(false);
        readyButton.setPreferredSize(new Dimension(200, 60));

        leaveButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        leaveButton.setBackground(new Color(178, 34, 34));
        leaveButton.setForeground(Color.WHITE);
        leaveButton.setFocusPainted(false);
        leaveButton.setPreferredSize(new Dimension(200, 60));

        bottomPanel.add(readyButton);
        bottomPanel.add(leaveButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updatePlayerSlot(int index, String playerName) {
        if (index >= 0 && index < 4) {
            playerLabels[index].setText("🎮 " + playerName);
        }
    }

    public void clearPlayerSlot(int index) {
        if (index >= 0 && index < 4) {
            playerLabels[index].setText("🧍 Waiting...");
        }
    }

    public void clearAllSlots() {
        for (int i = 0; i < 4; i++) {
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
