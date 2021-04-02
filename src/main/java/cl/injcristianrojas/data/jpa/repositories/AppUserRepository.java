package cl.injcristianrojas.data.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>, AppUserRepositoryCustom {
    AppUser findByUsername(String username);
}
