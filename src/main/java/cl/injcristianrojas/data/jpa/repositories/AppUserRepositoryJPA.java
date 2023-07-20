package cl.injcristianrojas.data.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.UserJPA;

@Repository
public interface AppUserRepositoryJPA extends JpaRepository<UserJPA, Long>, AppUserRepositoryJPACustom {
    UserJPA findByUsername(String username);
}
