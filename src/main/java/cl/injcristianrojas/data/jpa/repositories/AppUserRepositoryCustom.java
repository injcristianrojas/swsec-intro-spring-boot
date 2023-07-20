package cl.injcristianrojas.data.jpa.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.UserJPA;

@Repository
public interface AppUserRepositoryCustom {
    List<UserJPA> getUsersByRoleId(String roleId);
}
