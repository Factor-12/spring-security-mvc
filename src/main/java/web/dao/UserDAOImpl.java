package web.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public UserDAOImpl() {
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public void removeUser(long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT user FROM User user WHERE user.username =:username", User.class);

        return query.setParameter("username", username).getSingleResult();
    }

    @Override
    public Role getRole(String role) {
        TypedQuery<Role> query = entityManager
                .createQuery("SELECT role FROM Role role WHERE role.name = :role", Role.class);
        return query.setParameter("role", role).getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role").getResultList();
    }
}