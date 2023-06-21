package springboot.springboot.service;

import springboot.springboot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(User user) throws Exception;

    void updateUser(User user, int id);

    User readUser(int id);

    void deleteUser(int id);
}
