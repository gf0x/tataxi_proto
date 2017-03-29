package app.dao;

import app.entity.User;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface UserDao {

    User get(String login);
    int insert(User user);
    void update(User user);
    void remove(User user);
    boolean ifExists(User user);
}
