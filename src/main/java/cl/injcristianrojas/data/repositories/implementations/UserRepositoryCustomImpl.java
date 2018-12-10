package cl.injcristianrojas.data.repositories.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.ApplicationUser;
import cl.injcristianrojas.data.repositories.ApplicationUserRepositoryCustom;

@Repository
@Transactional
public class UserRepositoryCustomImpl implements ApplicationUserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<ApplicationUser> getUsersByRoleId(String roleId) {
		Query query =  entityManager.createNativeQuery("SELECT * FROM users where role_id = " + roleId, ApplicationUser.class);
		return query.getResultList();
	}

}
