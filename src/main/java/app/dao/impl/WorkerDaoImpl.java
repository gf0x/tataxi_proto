package app.dao.impl;

import app.dao.WorkerDao;
import app.entity.Worker;
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
@Cacheable("workers")
public class WorkerDaoImpl implements WorkerDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(WorkerDaoImpl.class.getSimpleName());

    private static final String GET = "SELECT login, pass_data, full_name, is_driver, phone_num, dept_id FROM worker WHERE login=?";
    private static final String INSERT = "INSERT INTO worker (login, pass_data, full_name, is_driver, phone_num, dept_id, online) " +
            "VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE worker SET pass_data=?, full_name=?, is_driver=?, phone_num=?, dept_id=?, online=? " +
            "WHERE login=?";
    private static final String DELETE = "DELETE FROM worker WHERE login=?";

    public Worker get(String login) {
        logger.info("DAO: grabbing object Worker from DB");
        return jdbcTemplate.queryForObject(GET, mapper, login);
    }

    public int insert(Worker worker) {
        logger.info("DAO: inserting object Worker into DB");
        return jdbcTemplate.update(INSERT, worker.getLogin(), worker.getPassportData(), worker.getFullName(), worker.isDriver(),
                worker.getPhoneNumber(), worker.getDeptId(), worker.isOnline());
    }

    public void update(Worker worker) {
        logger.info("DAO: updating object Worker in DB");
        jdbcTemplate.update(UPDATE, worker.getPassportData(), worker.getFullName(), worker.isDriver(),
                worker.getPhoneNumber(), worker.getDeptId(), worker.isOnline(), worker.getLogin());
    }

    public void remove(Worker worker) {
        logger.info("DAO: removing object Worker from DB");
        jdbcTemplate.update(DELETE, worker.getLogin());
    }

    private RowMapper<Worker> mapper = new RowMapper<Worker>() {
        public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
            Worker worker = new Worker();
            worker.setLogin(rs.getString("login"));
            worker.setPassportData(rs.getString("pass_data"));
            worker.setFullName(rs.getString("full_name"));
            worker.setDriver(rs.getBoolean("is_driver"));
            worker.setPhoneNumber(rs.getString("phone_num"));
            worker.setDeptId(rs.getInt("dept_id"));
            worker.setOnline(rs.getBoolean("online"));
            return worker;
        }
    };
}