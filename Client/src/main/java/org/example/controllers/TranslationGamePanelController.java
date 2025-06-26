package org.example.controllers;

import lombok.Getter;
import lombok.Setter;
import org.example.model.ClientConnectionHandler;
import org.example.model.ClientContext;
import org.example.model.MessageFactory;
import org.example.model.games.GamesName;
import org.example.view.TranslationGamePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Component
public class TranslationGamePanelController {
    @Getter @Setter
    private String chineseWord;
    @Getter @Setter
    private String correctTranslation;
    @Getter @Setter
    private List<String> options;
    private Integer move = 0;
    private final TranslationGamePanel panel;
    private final ClientConnectionHandler clientConnectionHandler;
    private final ClientContext context;

    private final static Logger logger = Logger.getLogger(TranslationGamePanelController.class.getName());

    @Autowired
    public TranslationGamePanelController(TranslationGamePanel panel, ClientConnectionHandler clientConnectionHandler, ClientContext context) {
        this.panel = panel;
        this.clientConnectionHandler = clientConnectionHandler;
        this.context = context;

        Arrays.stream(panel.getAnswerButtons())
                .forEach(button -> button.addActionListener(e -> sendAnswer(button.getText())));
    }

    private void sendAnswer(String answer) {
        String msg = MessageFactory.sendAnswer(GamesName.TranslationGame.toString(), context.getUsername(), this.getMove(), this.createArgs(answer));
        clientConnectionHandler.sendMessage(msg);
    }

    private String createArgs(String answer) {
        StringBuilder args = new StringBuilder();
        args.append(this.getChineseWord());
        args.append(",");
        args.append(answer);
        return args.toString();
    }

    public void createQuestionFromArgs(String args) {
        String[] splitArgs = args.split(",");
        this.chineseWord = splitArgs[0];
        this.correctTranslation = splitArgs[1];
        this.options = new ArrayList<>(List.of(splitArgs[2], splitArgs[3], splitArgs[4]));
    }

    public void setPanelArgs() {
        panel.setQuestion(this.getChineseWord());
        logger.info("set question to " + this.getChineseWord());

        List<String> allOptions = this.getShuffledOptions();
        logger.info("set options to " + allOptions);
        for (int i = 0; i < 4; i++) {
            panel.getAnswerButtons()[i].setText(allOptions.get(i));
        }
    }

    private List<String> getShuffledOptions() {
        List<String> allOptions = new ArrayList<>(this.options);
        allOptions.add(this.correctTranslation);
        Collections.shuffle(allOptions);
        return allOptions;
    }

    private Integer getMove() {
        return move++;
    }
}
