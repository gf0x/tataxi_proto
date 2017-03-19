package app.dao.impl;

import app.dao.UserDao;
import app.entity.User;
import org.apache.log4j.lf5.LF5Appender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
public class UserDaoImpl implements UserDao{

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class.getSimpleName());

    public User get(String login) {
        logger.info("DAO: grabbing object User from DB");
        return null;
    }

    public User insert(User user) {
        logger.info("DAO: inserting object User into DB");
        return null;
    }

    public void update(User user) {
        logger.info("DAO: updating object User in DB");
    }

    public void remove(User user) {
        logger.info("DAO: removing object User from DB");
    }
}
