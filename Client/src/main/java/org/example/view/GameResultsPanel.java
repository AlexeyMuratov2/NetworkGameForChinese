package org.example.view;


import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Getter
@Component
public class GameResultsPanel extends JPanel implements Panel{

    private static final int MAX_PLAYERS = 4;
    private final List<PlayerResult> results = new ArrayList<>();
    private final JPanel resultsPanel = new JPanel();
    private final JLabel titleLabel = new JLabel("🏆 Game Results", SwingConstants.CENTER);
    private final JButton playAgainButton = new JButton("✅ Play Again");
    private final JButton exitButton = new JButton("🚪 Exit Game");
    private BufferedImage backgroundImage;

    public GameResultsPanel() {
        setPreferredSize(new Dimension(1280, 900));
        setLayout(new BorderLayout());

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/background.jpg")));
        } catch (IOException | NullPointerException e) {
            System.err.println("Failed to load background: " + e.getMessage());
        }

        // Title
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setForeground(new Color(178, 34, 34));
        titleLabel.setBorder(new EmptyBorder(40, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Results
        resultsPanel.setOpaque(false);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBorder(new EmptyBorder(30, 400, 30, 400));
        add(resultsPanel, BorderLayout.CENTER);

        // Bottom buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setOpaque(false);

        playAgainButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        playAgainButton.setBackground(new Color(60, 179, 113));
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setFocusPainted(false);
        playAgainButton.setPreferredSize(new Dimension(220, 60));

        exitButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        exitButton.setBackground(new Color(178, 34, 34));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setPreferredSize(new Dimension(220, 60));

        buttonPanel.add(playAgainButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addPlayerResult(String playerName, int score) {
        if (results.size() >= MAX_PLAYERS) return;
        results.add(new PlayerResult(playerName, score));
        this.showResults();
    }

    public void showResults() {
        results.sort((a, b) -> Integer.compare(b.score, a.score));
        resultsPanel.removeAll();

        int rank = 1;
        for (PlayerResult result : results) {
            JPanel panel = createPlayerResultPanel(rank++, result);
            resultsPanel.add(Box.createVerticalStrut(20));
            resultsPanel.add(panel);
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    public void resetResults() {
        results.clear();
        resultsPanel.removeAll();
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private JPanel createPlayerResultPanel(int rank, PlayerResult result) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(new Color(255, 255, 255, 210));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                new EmptyBorder(20, 30, 20, 30)
        ));

        JLabel nameLabel = new JLabel("🏅 " + rank + ". " + result.playerName, SwingConstants.LEFT);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        nameLabel.setForeground(Color.BLACK);

        JLabel scoreLabel = new JLabel("Score: " + result.score, SwingConstants.RIGHT);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        scoreLabel.setForeground(new Color(25, 25, 112));

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(scoreLabel, BorderLayout.EAST);

        return panel;
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

    @Override
    public String getPanelName() {
        return Panels.GAME_RESULTS.toString();
    }

    @Override
    public java.awt.Component asComponent() {
        return this;
    }

    private static class PlayerResult {
        String playerName;
        int score;

        PlayerResult(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }
    }
}
