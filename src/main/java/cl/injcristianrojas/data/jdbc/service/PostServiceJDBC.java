package cl.injcristianrojas.data.jdbc.service;

import cl.injcristianrojas.data.jdbc.model.PostJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostServiceJDBC {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<PostJDBC> findAll() {
        return new ArrayList<>(jdbcTemplate.query("SELECT * FROM posts", (rs, rowNum) -> new PostJDBC(rs.getLong("id"), rs.getString("message"))));
    }

    public PostJDBC save(PostJDBC postJDBC) {
        jdbcTemplate.batchUpdate("INSERT INTO posts(message) VALUES ('" + postJDBC.getMessage() + "')");
        return jdbcTemplate.queryForObject("SELECT * FROM posts WHERE message = '" + postJDBC.getMessage() + "'", (rs, RowNum) -> new PostJDBC(rs.getLong("id"), rs.getString("message")));
    }

}
