package cl.injcristianrojas.data.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.RoleJPA;

@Repository
public interface RoleRepositoryJPA extends JpaRepository<RoleJPA, Long> {
	RoleJPA findByRolename(String rolename);
}
