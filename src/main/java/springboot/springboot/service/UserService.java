package springboot.springboot.service;

import springboot.springboot.model.User;

public interface UserService {
    Object getAllUsers();

    void createUser(User user);

    void updateUser(User user, int id);

    User readUser(int id);

    void deleteUser(int id);
}
