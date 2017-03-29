package app.dao.impl;

import app.dao.UserDao;
import app.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
@Cacheable("users")
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class.getSimpleName());

    private static final String GET = "SELECT login, pswd, auth_role, enabled FROM \"user\" WHERE login=?";
    private static final String INSERT = "INSERT INTO \"user\" (login, pswd, auth_role, enabled) SELECT ?,md5(?),?,? WHERE NOT exists(SELECT login FROM \"user\" WHERE login = ?)";
    private static final String UPDATE = "UPDATE \"user\" SET pswd=md5(?), auth_role=?, enabled=? WHERE login=?";
    private static final String DELETE = "DELETE FROM \"user\" WHERE login=?";

    private static final String IF_EXISTS = "SELECT exists (SELECT login FROM \"user\" WHERE login = ?)";

    public User get(String login) {
        logger.info("DAO: grabbing object User from DB");
        return jdbcTemplate.queryForObject(GET, mapper, login);
    }

    public int insert(User user) {
        logger.info("DAO: inserting object User into DB");
        if(ifExists(user))
            return -1;
        return jdbcTemplate.update(INSERT, user.getLogin(), user.getPswd(), user.getAuthRole(), user.getEnabled(), user.getLogin());
    }

    public void update(User user) {
        logger.info("DAO: updating object User in DB");
        jdbcTemplate.update(UPDATE, user.getPswd(), user.getAuthRole(), user.getEnabled(), user.getLogin());
    }

    public void remove(User user) {
        logger.info("DAO: removing object User from DB");
        jdbcTemplate.update(DELETE, user.getLogin());
    }

    public boolean ifExists(User user){
        logger.info("DAO: if exist User "+user);
        return jdbcTemplate.queryForObject(IF_EXISTS, Boolean.class, user.getLogin());
    }

    private RowMapper<User> mapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setLogin(rs.getString("login"));
            user.setPswd(rs.getString("pswd"));
            user.setAuthRole(rs.getString("auth_role"));
            user.setEnabled(rs.getInt("enabled"));
            return user;
        }
    };
}