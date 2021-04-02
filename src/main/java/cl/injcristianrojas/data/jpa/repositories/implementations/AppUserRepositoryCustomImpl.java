package cl.injcristianrojas.data.jpa.repositories.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.User;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepositoryCustom;

@Repository
@Transactional
public class AppUserRepositoryCustomImpl implements AppUserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<User> getUsersByUsername(String username) {
		TypedQuery<User> query = entityManager.createQuery("from AppUser where username = '" + username + "'", User.class);
		return query.getResultList();
	}

	@Override
	public List<User> getUsersByType(String type) {
		TypedQuery<User> query = entityManager.createQuery("from AppUser where role_id = " + type, User.class);
		return query.getResultList();
	}

}
