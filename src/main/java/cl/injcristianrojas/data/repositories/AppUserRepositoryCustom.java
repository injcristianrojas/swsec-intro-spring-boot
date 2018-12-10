package cl.injcristianrojas.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.AppUser;

@Repository
public interface AppUserRepositoryCustom {
    List<AppUser> getUsersByRoleId(String roleId);
}
