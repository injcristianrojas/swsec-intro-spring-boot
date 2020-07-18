package cl.injcristianrojas.data.repositories.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.AppUser;
import cl.injcristianrojas.data.repositories.AppUserRepositoryCustom;

@Repository
@Transactional
public class UserRepositoryCustomImpl implements AppUserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<AppUser> getUsersByUsername(String username) {
		Query query = entityManager.createNativeQuery("SELECT * FROM users where username = '" + username + "'");
		return query.getResultList();
	}

}
