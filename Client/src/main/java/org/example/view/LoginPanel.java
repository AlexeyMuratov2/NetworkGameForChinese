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
public class LoginPanel extends JPanel {
    private static LoginPanel instance;
    private final JLabel titleLabel = new JLabel("汉语冒险 | Chinese Quest", SwingConstants.CENTER);
    private final JButton loginButton = new JButton("🎮 PLay");
    private final JButton switchToRegisterButton = new JButton("📝 Register");
    private final JTextField loginField = new JTextField(20);
    private final JPasswordField passField = new JPasswordField(20);
    private BufferedImage backgroundImage;

    private LoginPanel() {
        setPreferredSize(new Dimension(1280, 900));

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/background.jpg")));
        } catch (IOException | NullPointerException e) {
            System.err.println("Фон не загружен: " + e.getMessage());
        }

        setLayout(new BorderLayout());

        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setForeground(new Color(200, 190, 0));
        titleLabel.setBorder(new EmptyBorder(80, 10, 20, 10)); // 🔁 увеличен верхний отступ
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
//                g.setColor(new Color(0, 0, 0, 100));
//                g.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                super.paintComponent(g);
            }
        };
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(120, 400, 60, 400)); // 🔁 увеличен верхний отступ

        Font fieldFont = new Font("SansSerif", Font.PLAIN, 24);
        loginField.setFont(fieldFont);
        passField.setFont(fieldFont);
        loginField.setMaximumSize(new Dimension(600, 50));
        passField.setMaximumSize(new Dimension(600, 50));

        JLabel userLabel = new JLabel("👤 Username:");
        userLabel.setFont(fieldFont);
        userLabel.setForeground(Color.BLACK);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passLabel = new JLabel("🔒 Password:");
        passLabel.setFont(fieldFont);
        passLabel.setForeground(Color.BLACK);
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.setFont(new Font("SansSerif", Font.BOLD, 28));
        loginButton.setBackground(new Color(178, 34, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setMaximumSize(new Dimension(600, 60));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        switchToRegisterButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        switchToRegisterButton.setBackground(new Color(30, 144, 255));
        switchToRegisterButton.setForeground(Color.WHITE);
        switchToRegisterButton.setFocusPainted(false);
        switchToRegisterButton.setMaximumSize(new Dimension(600, 50));
        switchToRegisterButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(userLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(loginField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(passLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(passField);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(loginButton);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(switchToRegisterButton);

        add(centerPanel, BorderLayout.CENTER);
    }

    public static LoginPanel getInstance(){
        if(instance != null){
            synchronized (LoginPanel.class){
                if(instance != null){
                    instance = new LoginPanel();
                }
            }
        }
        return instance;
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
