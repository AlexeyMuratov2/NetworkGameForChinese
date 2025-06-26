package org.example.model.games;

import org.example.controllers.TranslationGamePanelController;
import org.example.model.ClientContext;
import org.example.view.MainFrame;
import org.example.view.Panels;
import org.example.view.TranslationGamePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TranslationGameStrategy implements GameStrategy{
    private final String gameName = GamesName.TranslationGame.toString();
    private final Logger logger = Logger.getLogger(TranslationGameStrategy.class.getName());
    private final MainFrame mainFrame;
    private final ClientContext clientContext;
    private final TranslationGamePanel translationGamePanel;
    private final TranslationGamePanelController controller;

    @Autowired
    public TranslationGameStrategy(MainFrame mainFrame, ClientContext clientContext, TranslationGamePanel translationGamePanel, TranslationGamePanelController controller) {
        this.mainFrame = mainFrame;
        this.clientContext = clientContext;
        this.translationGamePanel = translationGamePanel;
        this.controller = controller;
    }

    @Override
    public String getGameName() {
        return gameName;
    }

    @Override
    public void start(String playersInSession, String args) {
        makeMove(args);
        mainFrame.switchTo(Panels.TRANSLATION_GAME);
        logger.info("Starting translation game with args: " + args);
    }

    public void makeMove(String args) {
        controller.createQuestionFromArgs(args);
        controller.setPanelArgs();
        logger.info("Moving translation game with args: " + args);
    }
}
