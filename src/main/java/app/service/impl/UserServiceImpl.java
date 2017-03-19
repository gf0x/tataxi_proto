package app.service.impl;

import app.dao.UserDao;
import app.entity.User;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getSimpleName());

    public User get(String login) {
        logger.info("SERVICE: grabbing object User from DB");
        return userDao.get(login);
    }

    public User insert(User user) {
        logger.info("SERVICE: inserting object User into DB");
        return userDao.insert(user);
    }

    public void update(User user) {
        logger.info("SERVICE: updating object User in DB");
        userDao.update(user);
    }

    public void remove(User user) {
        logger.info("SERVICE: removing object User from DB");
        userDao.remove(user);
    }
}
