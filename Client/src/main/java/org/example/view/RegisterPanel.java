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
public class RegisterPanel extends JPanel {
    private static RegisterPanel instance;

    private final JLabel titleLabel = new JLabel("Create Your Account", SwingConstants.CENTER);
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JPasswordField confirmPasswordField = new JPasswordField(20);
    private final JButton registerButton = new JButton("📝 Register");
    private final JButton switchToLoginButton = new JButton("🔙 Back to Login");
    private final JLabel errorLabel = new JLabel("Passwords do not match", SwingConstants.CENTER);

    private BufferedImage backgroundImage;

    private RegisterPanel() {
        setPreferredSize(new Dimension(1280, 900));
        setLayout(new BorderLayout());

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/background.jpg")));
        } catch (IOException | NullPointerException e) {
            System.err.println("Background not loaded: " + e.getMessage());
        }

        // Title
        titleLabel.setFont(new Font("Serif", Font.BOLD, 44));
        titleLabel.setForeground(new Color(200, 190, 0));
        titleLabel.setBorder(new EmptyBorder(80, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(60, 400, 60, 400));

        Font fieldFont = new Font("SansSerif", Font.PLAIN, 24);
        Font labelFont = new Font("SansSerif", Font.BOLD, 22);

        // Labels
        JLabel usernameLabel = new JLabel("👤 Username:");
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("🔒 Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel confirmPasswordLabel = new JLabel("✅ Confirm Password:");
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordLabel.setForeground(Color.BLACK);
        confirmPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Fields
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        confirmPasswordField.setFont(fieldFont);
        Dimension fieldSize = new Dimension(600, 50);
        usernameField.setMaximumSize(fieldSize);
        passwordField.setMaximumSize(fieldSize);
        confirmPasswordField.setMaximumSize(fieldSize);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Error label
        errorLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 28));
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setMaximumSize(new Dimension(600, 60));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        switchToLoginButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        switchToLoginButton.setBackground(new Color(30, 144, 255));
        switchToLoginButton.setForeground(Color.WHITE);
        switchToLoginButton.setFocusPainted(false);
        switchToLoginButton.setMaximumSize(new Dimension(600, 50));
        switchToLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components
        centerPanel.add(usernameLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(usernameField);
        centerPanel.add(Box.createVerticalStrut(20));

        centerPanel.add(passwordLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(passwordField);
        centerPanel.add(Box.createVerticalStrut(20));

        centerPanel.add(confirmPasswordLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(confirmPasswordField);
        centerPanel.add(Box.createVerticalStrut(20));

        centerPanel.add(errorLabel); // 🔴 error message

        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(registerButton);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(switchToLoginButton);

        add(centerPanel, BorderLayout.CENTER);
    }

    public static RegisterPanel getInstance() {
        if (instance == null) {
            synchronized (RegisterPanel.class) {
                if (instance == null) {
                    instance = new RegisterPanel();
                }
            }
        }
        return instance;
    }

    public void showPasswordMismatchMessage() {
        errorLabel.setVisible(true);
        revalidate();
        repaint();
    }

    public void hidePasswordMismatchMessage() {
        errorLabel.setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(0, 0, new Color(139, 0, 0), 0, getHeight(), new Color(255, 215, 0));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
