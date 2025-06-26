package org.example.commands.gameCommands;

import org.example.commands.Command;
import org.example.commands.GameCommandParser;
import org.example.model.games.GameStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartGameCommand implements Command {
    @Autowired
    private GameStrategyFactory gameStrategyFactory;

    @Override
    public String getName() {
        return "startGame";
    }

    @Override
    public void execute(String args) {
        String[] parts = args.split(" ");
        String gameName = parts[0];
        String playersInSession = parts[1];
        String msgArgs = GameCommandParser.extractFirstBracketContent(args).orElse("");


        gameStrategyFactory.getGameByName(gameName).start(playersInSession, msgArgs);
    }
}
