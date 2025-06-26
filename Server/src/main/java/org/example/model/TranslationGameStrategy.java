package org.example.model;

import org.example.database.TranslationGameQuestion;
import org.example.database.TranslationGameRepository;
import org.example.gameData.GameDataFactory;
import org.example.gameData.GamesName;
import org.example.gameData.PlayerGameData;
import org.example.gameData.TranslationGameUserData;
import org.example.network.ClientManager;
import org.example.network.GameSession;
import org.example.network.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TranslationGameStrategy implements GameStrategy{
    private final String gameName = "TranslationGame";
    private final TranslationGameRepository repository;
    private final SessionManager sessionManager;
    private final GameDataFactory gameDataFactory;
    private final static Logger logger = Logger.getLogger(TranslationGameStrategy.class.getName());

    @Autowired
    public TranslationGameStrategy(TranslationGameRepository repository, SessionManager sessionManager, GameDataFactory gameDataFactory) {
        this.repository = repository;
        this.sessionManager = sessionManager;
        this.gameDataFactory = gameDataFactory;
    }

    @Override
    public String getGameName() {
        return gameName;
    }

    @Override
    public void start(GameSession session) {
        TranslationGameQuestion question = repository.getRandomQuestion();
        session.sendMessageToSession(MessageFactory.startGame(gameName, session.getDisplayName(), question.getArgs()));
    }

    @Override
    public String getOnMoveMessage(String userName, int move, String args) {
        String[] parts = args.split(",");
        String question = parts[0];
        String answer = parts[1];
        logger.info("Translation game onMove with params: " + move + " " + question + " " + answer);
        String usersInSession = sessionManager.getSessionByName(userName).getDisplayName();

        TranslationGameUserData data = gameDataFactory
                .getOrCreateGameData(GamesName.TranslationGame, userName, TranslationGameUserData.class);

        boolean isCorrect = Boolean.TRUE.equals(repository.isCorrectAnswer(question, answer));
        data.addScore(isCorrect ? 1 : 0);
        TranslationGameQuestion nextQuestion = repository.getRandomQuestion();
        String msg = MessageFactory.makeMove(gameName, usersInSession, nextQuestion.getArgs());
        return msg;
    }


}
