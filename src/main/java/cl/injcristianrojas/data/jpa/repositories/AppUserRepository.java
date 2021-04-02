package cl.injcristianrojas.data.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.User;

@Repository
public interface AppUserRepository extends JpaRepository<User, Long>, AppUserRepositoryCustom {
    User findByUsername(String username);
}
