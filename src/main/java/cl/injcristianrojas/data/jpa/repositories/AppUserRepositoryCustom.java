package cl.injcristianrojas.data.jpa.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.User;

@Repository
public interface AppUserRepositoryCustom {
    List<User> getUsersByUsername(String username);
    List<User> getUsersByType(String type);
}
