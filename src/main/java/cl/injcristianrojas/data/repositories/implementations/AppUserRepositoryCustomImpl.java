package cl.injcristianrojas.data.repositories.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.AppUser;
import cl.injcristianrojas.data.repositories.AppUserRepositoryCustom;

@Repository
@Transactional
public class AppUserRepositoryCustomImpl implements AppUserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<AppUser> getUsersByUsername(String username) {
		TypedQuery<AppUser> query = entityManager.createQuery("from AppUser where username = '" + username + "'", AppUser.class);
		return query.getResultList();
	}

	@Override
	public List<AppUser> getUsersByType(String type) {
		TypedQuery<AppUser> query = entityManager.createQuery("from AppUser where role_id = " + type, AppUser.class);
		return query.getResultList();
	}

}
