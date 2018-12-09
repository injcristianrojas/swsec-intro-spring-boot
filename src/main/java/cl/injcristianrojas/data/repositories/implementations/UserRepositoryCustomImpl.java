package cl.injcristianrojas.data.repositories.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.User;
import cl.injcristianrojas.data.repositories.UserRepositoryCustom;

@Repository
@Transactional
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<User> getUsersByRoleId(String roleId) {
		Query query =  entityManager.createNativeQuery("SELECT * FROM users where role_id = " + roleId, User.class);
		return query.getResultList();
	}

}
