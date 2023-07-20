package cl.injcristianrojas.data.jpa.repositories.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.UserJPA;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepositoryCustom;

@Repository
@Transactional
public class UserRepositoryCustomImpl implements AppUserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<UserJPA> getUsersByRoleId(String roleId) {
		Query query =  entityManager.createNativeQuery("SELECT * FROM users where role_id = " + roleId, UserJPA.class);
		return query.getResultList();
	}

}
