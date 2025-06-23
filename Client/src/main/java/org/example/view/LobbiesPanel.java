package org.example.view;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.example.model.ClientContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@org.springframework.stereotype.Component
public class LobbiesPanel extends JPanel {

    private final ClientContext clientContext;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    @Getter
    private JList<String> lobbyList;
    @Getter
    private final JButton usernameButton;
    @Getter
    private final JButton refreshButton = new JButton("🔄 Refresh");
    @Getter
    private final JButton createLobbyButton = new JButton("➕ Create Lobby");
    private BufferedImage backgroundImage;
    private int hoverIndex = -1;

    @Autowired
    public LobbiesPanel(ClientContext clientContext) {
        this.clientContext = clientContext;

        setPreferredSize(new Dimension(1280, 900));
        setLayout(new BorderLayout());

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/background.jpg")));
        } catch (IOException | NullPointerException e) {
            System.err.println("Failed to load background: " + e.getMessage());
        }

        usernameButton = new JButton(); // инициализируем пусто, потом зададим текст
        setupUI();
    }

    @PostConstruct
    public void initPanel() {
        refreshUsername(); // теперь clientContext точно не null
    }

    private void setupUI() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20, 20, 0, 40));

        usernameButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        usernameButton.setBackground(new Color(70, 130, 180));
        usernameButton.setForeground(Color.WHITE);
        usernameButton.setFocusPainted(false);

        refreshButton.setFont(new Font("SansSerif", Font.PLAIN, 16)); // === NEW ===
        createLobbyButton.setFont(new Font("SansSerif", Font.PLAIN, 16)); // === NEW ===

        Color mainRed = new Color(178, 34, 34);
        Color mainBlue = new Color(30, 144, 255);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);

        refreshButton.setFont(buttonFont);
        refreshButton.setBackground(mainRed);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setPreferredSize(new Dimension(180, 50));

        createLobbyButton.setFont(buttonFont);
        createLobbyButton.setBackground(mainBlue);
        createLobbyButton.setForeground(Color.WHITE);
        createLobbyButton.setFocusPainted(false);
        createLobbyButton.setPreferredSize(new Dimension(180, 50));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        bottomPanel.setOpaque(false);


        refreshButton.setFont(buttonFont);
        refreshButton.setBackground(mainRed);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setPreferredSize(new Dimension(200, 60));

        createLobbyButton.setFont(buttonFont);
        createLobbyButton.setBackground(mainBlue);
        createLobbyButton.setForeground(Color.WHITE);
        createLobbyButton.setFocusPainted(false);
        createLobbyButton.setPreferredSize(new Dimension(200, 60));

        bottomPanel.add(refreshButton);
        bottomPanel.add(createLobbyButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Правая панель с кнопкой пользователя
        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonWrapper.setOpaque(false);
        buttonWrapper.add(usernameButton);
        topPanel.add(buttonWrapper, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ===== Лист лобби =====
        listModel.addElement("🏯 Lobby 1 - Beginner");
        listModel.addElement("🐉 Lobby 2 - Intermediate");
        listModel.addElement("🧧 Lobby 3 - Advanced");
        listModel.addElement("🌸 Lobby 4 - Private");

        lobbyList = new JList<>(listModel);
        lobbyList.setFont(new Font("SansSerif", Font.PLAIN, 24));
        lobbyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lobbyList.setFixedCellHeight(50);
        lobbyList.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        lobbyList.setBackground(new Color(255, 255, 255, 200));
        lobbyList.setForeground(Color.BLACK);
        lobbyList.setSelectionBackground(new Color(255, 215, 0));
        lobbyList.setSelectionForeground(Color.BLACK);

        lobbyList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == hoverIndex && !isSelected) {
                    c.setBackground(Color.LIGHT_GRAY);
                }
                return c;
            }
        });

        lobbyList.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = lobbyList.locationToIndex(e.getPoint());
                if (index != hoverIndex) {
                    hoverIndex = index;
                    lobbyList.repaint();
                }
            }
        });

        lobbyList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoverIndex = -1;
                lobbyList.repaint();
            }
        });

        JScrollPane scrollPane = new JScrollPane(lobbyList);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(600, 500));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(100, 340, 100, 340));

        JLabel titleLabel = new JLabel("Select a Lobby", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setForeground(new Color(178, 34, 34));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(scrollPane);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void refreshUsername() {
        String username = clientContext.getUsername();
        usernameButton.setText("👤 " + username);
    }

    public void setLobbyList(String lobbiesName){
        listModel.removeAllElements();
        for (String lobbyName : lobbiesName.split(" ")) {
            listModel.addElement(lobbyName);
        }
        refreshLobbyListView();
    }

    public void addLobby(String lobbyName) {
        if (!listModel.contains(lobbyName)) {
            listModel.addElement(lobbyName);
        }
        refreshLobbyListView();
    }

    public void removeLobby(String lobbyName) {
        listModel.removeElement(lobbyName);
        refreshLobbyListView();
    }

    public void refreshLobbyListView() {
        repaint();
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
