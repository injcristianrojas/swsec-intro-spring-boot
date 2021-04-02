package cl.injcristianrojas.data.jdbc.service;

import cl.injcristianrojas.data.jdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getUserData(String username) {
        List<User> toRet = new ArrayList<User>();
        jdbcTemplate.query("SELECT * FROM users WHERE username = " + username, (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("enabled"))).forEach(user -> toRet.add(user));
        return toRet;
    }

}
