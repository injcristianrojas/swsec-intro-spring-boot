package cl.injcristianrojas.data.jpa.repositories.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.UserJPA;
import cl.injcristianrojas.data.jpa.repositories.AppUserRepositoryCustom;

@Repository
@Transactional
public class AppUserRepositoryCustomImpl implements AppUserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserJPA> getUsersByUsername(String username) {
        TypedQuery<UserJPA> query = entityManager.createQuery("from UserJPA where username = '" + username + "'", UserJPA.class);
        return query.getResultList();
    }

    @Override
    public List<UserJPA> getUsersByType(String type) {
        TypedQuery<UserJPA> query = entityManager.createQuery("from UserJPA where role_id = " + type, UserJPA.class);
        return query.getResultList();
    }

}
