package org.example.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class DatabaseSetupRepository {
    private JdbcTemplate template;
    private static final Logger logger = Logger.getLogger(DatabaseSetupRepository.class.getName());

    public DatabaseSetupRepository(JdbcTemplate template){
        this.template = template;
        logger.info("setting up all tables");

        setUpLoginTable();
    }

    private void setUpLoginTable(){
        template.update("CREATE TABLE IF NOT EXISTS `chinese_quest_db`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `username` VARCHAR(45) NOT NULL,\n" +
                "  `passwd` VARCHAR(100) NOT NULL,\n" +
                "  PRIMARY KEY (`id`));");
    }

}
