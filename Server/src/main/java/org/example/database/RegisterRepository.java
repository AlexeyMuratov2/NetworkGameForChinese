package org.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class RegisterRepository {
    private JdbcTemplate template;
    private static final Logger logger = Logger.getLogger(RegisterRepository.class.getName());

    @Autowired
    public RegisterRepository(JdbcTemplate template) {
        this.template = template;
    }

    public boolean register(String username, String hashPass) {
        String checkSql = "SELECT COUNT(*) FROM users WHERE username = ?";
        String insertSql = "INSERT INTO users(username, passwd) VALUES (?, ?)";

        try {
            Integer count = template.queryForObject(checkSql, Integer.class, username);
            if (count != null && count > 0) {
                logger.info("username already exists: " + username);
                return false;
            }

            int rowsAffected = template.update(insertSql, username, hashPass);
            logger.info("new account successfully created for: " + username);
            return rowsAffected > 0;

        } catch (DataAccessException e) {
            logger.severe("error in db while creating new account for: " + username);
            return false;
        }
    }

}
