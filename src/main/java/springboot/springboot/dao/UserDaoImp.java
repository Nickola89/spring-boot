package springboot.springboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import springboot.springboot.model.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User user", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User readUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User userOld, int id) {
        User userNew = readUser(id);
        userNew.setName(userOld.getName());
        userNew.setLastName(userOld.getLastName());
        userNew.setAge(userOld.getAge());

    }

    @Override
    public void deleteUser(int id) {
        User userNew = entityManager.find(User.class, id);
        entityManager.remove(userNew);
    }

}
