package springboot.springboot.dao;

import springboot.springboot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    List<User> findEmail(String email);

    void createUser(User user);

    User readUser(int id);

    void updateUser(User userOld, int id);

    void deleteUser(int id);
}
