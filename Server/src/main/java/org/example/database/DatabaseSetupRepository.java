package org.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class DatabaseSetupRepository {
    private JdbcTemplate template;
    private static final Logger logger = Logger.getLogger(DatabaseSetupRepository.class.getName());

    @Autowired
    public DatabaseSetupRepository(JdbcTemplate template){
        this.template = template;
        logger.info("setting up all tables");

        setUpLoginTable();
        setUpGuessWordTable();
    }

    private void setUpLoginTable(){
        template.update("CREATE TABLE IF NOT EXISTS `chinese_quest_db`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `username` VARCHAR(45) NOT NULL,\n" +
                "  `passwd` VARCHAR(100) NOT NULL,\n" +
                "  PRIMARY KEY (`id`));");
    }

    private void setUpGuessWordTable(){
        template.update("CREATE TABLE IF NOT EXISTS guess_word_t (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    chinese_word VARCHAR(50) UNIQUE NOT NULL,\n" +
                "    english_translation VARCHAR(100) NOT NULL\n" +
                ");");

        template.update("INSERT IGNORE INTO guess_word_t (chinese_word, english_translation) VALUES\n" +
                "('你好', 'Hello'),\n" +
                "('谢谢', 'Thank you'),\n" +
                "('再见', 'Goodbye'),\n" +
                "('是', 'Yes'),\n" +
                "('不是', 'No'),\n" +
                "('中国', 'China');");
    }

}
