package org.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.logging.Logger;

@Repository
public class TranslationGameRepository {
    private JdbcTemplate template;
    private static final Logger logger = Logger.getLogger(TranslationGameRepository.class.getName());

    @Autowired
    public TranslationGameRepository(JdbcTemplate template) {
        this.template = template;
    }

    public TranslationGameQuestion getRandomQuestion() {
        // 1. Получаем случайное слово
        String sqlWord = "SELECT * FROM guess_word_t ORDER BY RAND() LIMIT 1";
        TranslationGameQuestion question = template.queryForObject(sqlWord, (rs, rowNum) -> {
            String chinese = rs.getString("chinese_word");
            String correct = rs.getString("english_translation");
            return new TranslationGameQuestion(chinese, correct, new ArrayList<>());
        });

        // 2. Получаем ровно 3 НЕПРАВИЛЬНЫХ перевода (исключаем правильный)
        String sqlWrong = "SELECT english_translation FROM guess_word_t " +
                "WHERE english_translation != ? ORDER BY RAND() LIMIT 3";
        List<String> wrongOptions = template.queryForList(sqlWrong, String.class, question.getCorrectTranslation());

        // 3. Добавляем правильный ответ в начало списка
        List<String> allOptions = new ArrayList<>();
        allOptions.addAll(wrongOptions);                   // затем 3 неправильных

        question.setOptions(allOptions);
        return question;
    }

    public boolean isCorrectAnswer(String chineseWord, String userAnswer) {
        String sql = "SELECT COUNT(*) FROM guess_word_t " +
                "WHERE chinese_word = ? AND english_translation = ?";
        Integer count = template.queryForObject(sql, Integer.class, chineseWord, userAnswer);
        return count != null && count > 0;
    }
}
