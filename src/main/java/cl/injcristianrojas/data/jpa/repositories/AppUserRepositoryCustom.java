package cl.injcristianrojas.data.jpa.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.AppUser;

@Repository
public interface AppUserRepositoryCustom {
    List<AppUser> getUsersByUsername(String username);
    List<AppUser> getUsersByType(String type);
}
