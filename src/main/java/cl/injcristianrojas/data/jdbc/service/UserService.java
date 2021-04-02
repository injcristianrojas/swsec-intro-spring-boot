package cl.injcristianrojas.data.jdbc.service;

import cl.injcristianrojas.data.jdbc.model.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UserJDBC> getUsersByUsername(String username) {
        List<UserJDBC> toRet = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM users WHERE username = '" + username + "'", (rs, rowNum) -> new UserJDBC(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("enabled"))).forEach(user -> toRet.add(user));
        return toRet;
    }

    public List<UserJDBC> findAll() {
        List<UserJDBC> toRet = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) -> new UserJDBC(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("enabled"))).forEach(user -> toRet.add(user));
        return toRet;
    }
}