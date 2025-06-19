package org.example.view;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Getter
public class RegisterPanel extends JPanel {
    private static RegisterPanel instance;
    private final JLabel titleLabel = new JLabel("Добро пожаловать в 汉语冒险!", SwingConstants.CENTER);
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JPasswordField confirmPasswordField = new JPasswordField(20);
    private final JButton registerButton = new JButton("📝 Зарегистрироваться");
    private final JButton switchToLoginButton = new JButton("🔙 Уже есть аккаунт?");

    private RegisterPanel() {
        setPreferredSize(new Dimension(1280, 900));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // 🔁 Явно белый фон

        // Заголовок
        titleLabel.setFont(new Font("Serif", Font.BOLD, 44));
        titleLabel.setForeground(new Color(178, 34, 34)); // Темно-красный
        titleLabel.setBorder(new EmptyBorder(80, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Центр: поля и кнопки
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(80, 400, 60, 400));

        Font fieldFont = new Font("SansSerif", Font.PLAIN, 24);
        Font labelFont = new Font("SansSerif", Font.BOLD, 22);

        // Метки и поля
        JLabel usernameLabel = new JLabel("👤 Имя пользователя:");
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(new Color(30, 30, 30)); // почти чёрный

        JLabel passwordLabel = new JLabel("🔒 Пароль:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(new Color(30, 30, 30));

        JLabel confirmPasswordLabel = new JLabel("✅ Повтор пароля:");
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordLabel.setForeground(new Color(30, 30, 30));

        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        confirmPasswordField.setFont(fieldFont);
        Dimension fieldSize = new Dimension(600, 50);
        usernameField.setMaximumSize(fieldSize);
        passwordField.setMaximumSize(fieldSize);
        confirmPasswordField.setMaximumSize(fieldSize);

        // Кнопки
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 28));
        registerButton.setBackground(new Color(34, 139, 34)); // тёмно-зелёный
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setMaximumSize(new Dimension(600, 60));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        switchToLoginButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        switchToLoginButton.setBackground(new Color(30, 144, 255)); // синий
        switchToLoginButton.setForeground(Color.WHITE);
        switchToLoginButton.setFocusPainted(false);
        switchToLoginButton.setMaximumSize(new Dimension(600, 50));
        switchToLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Добавляем элементы
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
        centerPanel.add(Box.createVerticalStrut(30));

        centerPanel.add(registerButton);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(switchToLoginButton);

        add(centerPanel, BorderLayout.CENTER);
    }

    public static RegisterPanel getInstance(){
        if(instance == null){
            synchronized (RegisterPanel.class){
                if (instance == null){
                    instance = new RegisterPanel();
                }
            }
        }
        return instance;
    }
}
