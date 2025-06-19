package org.example.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
    private final JdbcTemplate template;

    public LoginRepository(JdbcTemplate template){
        this.template = template;
    }

    public boolean isLoginSuccess(String username, String hashPass) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND passwd = ?";
        Integer count = template.queryForObject(sql, Integer.class, username, hashPass);
        return count != null && count > 0;
    }
}
