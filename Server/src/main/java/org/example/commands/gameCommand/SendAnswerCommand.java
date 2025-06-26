package org.example.commands.gameCommand;

import org.example.commands.Command;
import org.example.commands.GameCommandParser;
import org.example.database.TranslationGameRepository;
import org.example.model.GameStrategy;
import org.example.model.GameStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SendAnswerCommand implements Command {
    private final TranslationGameRepository repository;
    private final GameStrategyFactory gameStrategyFactory;
    private final Logger logger = Logger.getLogger(SendAnswerCommand.class.getName());

    @Autowired
    public SendAnswerCommand(TranslationGameRepository repository, GameStrategyFactory gameStrategyFactory) {
        this.repository = repository;
        this.gameStrategyFactory = gameStrategyFactory;
    }

    @Override
    public String getCommandName() {
        return "sendAnswer";
    }

    @Override
    public String execute(String args) {
        String[] parts = args.split(" ");
        String gameName = parts[0];
        String userName = parts[1];

        int move = Integer.parseInt(parts[2]);

        String msgArgs = GameCommandParser.extractFirstBracketContent(args).orElse("");
        GameStrategy game = gameStrategyFactory.getGameByName(gameName);
        String msg = game.getOnMoveMessage(userName, move, msgArgs);
        logger.info("message is: " + msg);
        return msg;

    }
}
