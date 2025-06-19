package org.example.database;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class RegisterRepository {
    private JdbcTemplate template;
    private static final Logger logger = Logger.getLogger(DatabaseSetupRepository.class.getName());

    public RegisterRepository(JdbcTemplate template) {
        this.template = template;
    }

    public boolean register(String username, String hashPass) {
        String sql = "INSERT INTO users(username, passwd) VALUES (?, ?)";
        try {
            int rowsAffected = template.update(sql, username, hashPass);
            logger.info("new account successfully created");
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            logger.severe("error in db while creating new account");
            return false;
        }
    }
}
