package org.example.view;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Component
@Getter
public class TranslationGamePanel extends JPanel {
    private BufferedImage backgroundImage;

    private final JLabel questionLabel = new JLabel("汉字", SwingConstants.CENTER);
    private final JButton[] answerButtons = new JButton[4];
    private final JLabel resultLabel = new JLabel("", SwingConstants.CENTER);

    public TranslationGamePanel() {
        setPreferredSize(new Dimension(1280, 900));
        setLayout(new BorderLayout());

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/background.jpg")));
        } catch (IOException | NullPointerException e) {
            System.err.println("Background image not loaded: " + e.getMessage());
        }

        setupUI();
    }

    @PostConstruct
    public void initPanel() {
        // Инициализация при запуске
        resultLabel.setVisible(false);
    }

    private void setupUI() {
        // Верхняя часть — вопрос
        questionLabel.setFont(new Font("Serif", Font.BOLD, 64));
        questionLabel.setForeground(new Color(178, 34, 34));
        questionLabel.setBorder(new EmptyBorder(100, 10, 20, 10));
        add(questionLabel, BorderLayout.NORTH);

        // Центр — надпись с результатом
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        resultLabel.setForeground(new Color(34, 139, 34));
        resultLabel.setBorder(new EmptyBorder(20, 10, 20, 10));
        add(resultLabel, BorderLayout.CENTER);

        // Нижняя часть — 4 кнопки с ответами
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 2, 30, 30));
        buttonPanel.setBorder(new EmptyBorder(50, 200, 100, 200));

        Font buttonFont = new Font("SansSerif", Font.BOLD, 28);
        Color buttonColor = new Color(70, 130, 180);

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton("Answer " + (i + 1));
            answerButtons[i].setFont(buttonFont);
            answerButtons[i].setBackground(buttonColor);
            answerButtons[i].setForeground(Color.WHITE);
            answerButtons[i].setFocusPainted(false);
            answerButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttonPanel.add(answerButtons[i]);
        }

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setQuestion(String question) {
        questionLabel.setText(question);
    }

    public void setAnswers(String[] answers) {
        for (int i = 0; i < 4 && i < answers.length; i++) {
            answerButtons[i].setText(answers[i]);
        }
    }

    public void setAnswerListener(ActionListener listener) {
        for (JButton button : answerButtons) {
            for (ActionListener old : button.getActionListeners()) {
                button.removeActionListener(old);
            }
            button.addActionListener(listener);
        }
    }

    public void showResult(String message, boolean isCorrect) {
        resultLabel.setText(message);
        resultLabel.setForeground(isCorrect ? new Color(0, 128, 0) : Color.RED);
        resultLabel.setVisible(true);
        revalidate();
        repaint();
    }

    public void hideResult() {
        resultLabel.setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
