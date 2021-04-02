package cl.injcristianrojas.data.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRolename(String rolename);
}
