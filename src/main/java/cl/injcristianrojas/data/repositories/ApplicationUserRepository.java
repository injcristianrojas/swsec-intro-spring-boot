package cl.injcristianrojas.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>, ApplicationUserRepositoryCustom {
    ApplicationUser findByUsername(String username);
}
