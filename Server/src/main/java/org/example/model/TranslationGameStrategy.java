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

import java.util.Set;
import java.util.logging.Logger;

@Component
public class TranslationGameStrategy implements GameStrategy {
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
        for (String username : session.getPlayers()){
            gameDataFactory
                    .getOrCreateGameData(GamesName.TranslationGame, username, TranslationGameUserData.class);
        }
        session.sendMessageToSession(MessageFactory.startGame(gameName, session.getDisplayName(), question.getArgs()));
    }

    @Override
    public String getOnMoveMessage(String username, int move, String args) {
        String[] parts = args.split(",");
        String question = parts[0];
        String answer = parts[1];
        logger.info("Translation game onMove with params: " + move + " " + question + " " + answer);
        GameSession session = sessionManager.getSessionByName(username);
        String usersInSession = session.getDisplayName();

        TranslationGameUserData data = gameDataFactory
                .getOrCreateGameData(GamesName.TranslationGame, username, TranslationGameUserData.class);

        data.setMove(move);
        if (!data.isGameFinished()) {
            boolean isCorrect = Boolean.TRUE.equals(repository.isCorrectAnswer(question, answer));
            data.addScore(isCorrect ? 1 : 0);
            TranslationGameQuestion nextQuestion = repository.getRandomQuestion();

            String msg = MessageFactory.makeMove(gameName, usersInSession, nextQuestion.getArgs());
            return msg;
        } else if (!isAllPlayersFinishedGame(session.getPlayers())){
            logger.info("player " + username + " finished the game with score " + data.getScore() + " with time " + data.getTime());

            return MessageFactory.ignoreMessage();
        } else {
            StringBuilder msgArgs = new StringBuilder();
            for(String userInSession : session.getPlayers()){
                TranslationGameUserData gameUserData = gameDataFactory.getOrCreateGameData(GamesName.TranslationGame, userInSession, TranslationGameUserData.class);
                msgArgs.append(gameUserData.getUsername());
                msgArgs.append(",");
                msgArgs.append(gameUserData.getScore());
                msgArgs.append(",");
                msgArgs.append(gameUserData.getTime());
                msgArgs.append(",");
            }
            msgArgs.deleteCharAt(msgArgs.length()-1);
            String msg = MessageFactory.finishGame(GamesName.TranslationGame.toString(), usersInSession, msgArgs.toString());
            session.sendMessageToSession(msg);
            return MessageFactory.ignoreMessage();
        }
    }

    private boolean isAllPlayersFinishedGame(Set<String> users){
        for(String username : users){
            if (!gameDataFactory.getOrCreateGameData(GamesName.TranslationGame, username, TranslationGameUserData.class).isGameFinished()){
                return false;
            }
        }
        return true;
    }
}
