package cl.injcristianrojas.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.ApplicationUser;

@Repository
public interface ApplicationUserRepositoryCustom {
    List<ApplicationUser> getUsersByRoleId(String roleId);
}
