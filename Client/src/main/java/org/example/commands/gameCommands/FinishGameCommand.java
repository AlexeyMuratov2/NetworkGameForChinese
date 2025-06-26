package org.example.commands.gameCommands;

import org.example.commands.Command;
import org.example.commands.GameCommandParser;
import org.example.view.GameResultsPanel;
import org.example.view.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.example.view.Panels;

import java.util.logging.Logger;

@Component
public class FinishGameCommand implements Command {
    private final MainFrame mainFrame;
    private final GameResultsPanel gameResultsPanel;
    private final static Logger logger = Logger.getLogger(FinishGameCommand.class.getName());

    @Autowired
    public FinishGameCommand(MainFrame mainFrame, GameResultsPanel gameResultsPanel) {
        this.mainFrame = mainFrame;
        this.gameResultsPanel = gameResultsPanel;
    }

    @Override
    public String getName() {
        return "finishGame";
    }

    @Override
    public void execute(String args) {
        String[] parts = args.split(" ");
        String gameName = parts[0];
        String usersInLobby = parts[1];

        String params = GameCommandParser.extractFirstBracketContent(args).orElse("");
        logger.info("Received finishGame params: " + params);
        String[] paramsParts = params.split(",");
        for (int i = 0; i< paramsParts.length; i+=3) {
            String username = paramsParts[i];
            String score = paramsParts[i+1];
            String time = paramsParts[i+2];
            logger.info("Username: " + username + ", score: " + score + ", time: " + time);
            gameResultsPanel.addPlayerResult(username, countScore(Integer.parseInt(score), Long.parseLong(time)));
            logger.info(String.valueOf(countScore(Integer.parseInt(score), Long.parseLong(time))));
        }
        mainFrame.switchTo(Panels.GAME_RESULTS);
    }

    private int countScore(int score, long timeMillis) {
        if (timeMillis <= 0) timeMillis = 1;
        long result = (long) score * score * 100000 / timeMillis;
        return (int) result;
    }
}
