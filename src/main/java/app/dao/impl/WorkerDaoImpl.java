package app.dao.impl;

import app.dao.WorkerDao;
import app.entity.Worker;
import org.postgresql.jdbc4.Jdbc4Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
//@Cacheable("workers")
public class WorkerDaoImpl implements WorkerDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(WorkerDaoImpl.class.getSimpleName());

    private static final String GET = "SELECT w.login, w.pass_data, w.full_name, w.is_driver, w.phone_num, w.dept_id, w.online, d_l.category FROM worker w LEFT JOIN driver_license d_l ON w.login = d_l.login WHERE w.login=?";
    private static final String INSERT = "INSERT INTO worker (login, pass_data, full_name, is_driver, phone_num, dept_id, online) " +
            "VALUES (?,?,?,?,?,?,?)";
    private static final String INSERT_LICENSES = "INSERT INTO driver_license (login, category) VALUES (?,?)";
    private static final String UPDATE = "UPDATE worker SET pass_data=?, full_name=?, is_driver=?, phone_num=?, dept_id=?, online=? " +
            "WHERE login=?";
    private static final String DELETE_LICENSES = "DELETE FROM driver_license WHERE login=?";
    private static final String DELETE = "DELETE FROM worker WHERE login=?";

    private static final String GET_FREE_BY_DISPATCHER = "SELECT w.login, w.pass_data, w.full_name, w.is_driver, w.phone_num, " +
            "w.dept_id, w.online, array_agg(d_l.category) AS licenses FROM worker w LEFT JOIN driver_license d_l ON w.login = d_l.login WHERE w.login NOT IN (SELECT worker_login FROM car_driver WHERE time_till IS NULL OR now() BETWEEN time_from AND" +
            " time_till) AND is_driver=TRUE AND dept_id=? GROUP BY w.login";
    private static final String SET_ONLINE = "UPDATE worker SET online=? WHERE login=?";

    public Worker get(String login) {
        logger.info("DAO: grabbing object Worker from DB");
        return jdbcTemplate.query(GET, mapperWithLicenses, login);
    }

    public List<Worker> getFreeByDispatcher(Worker dispatcher) throws Exception {
        logger.info("DAO: getting free drivers by dispatcher");
        if(dispatcher.getIsDriver())
            throw new Exception("Access denied: valid for dispatchers only");
        return jdbcTemplate.query(GET_FREE_BY_DISPATCHER, mapper, dispatcher.getDeptId());
    }

    public int insert(final Worker worker) {
        logger.info("DAO: inserting object Worker into DB");
        int rowsAffected = jdbcTemplate.update(INSERT, worker.getLogin(), worker.getPassportData(), worker.getFullName(), worker.getIsDriver(),
                worker.getPhoneNumber(), worker.getDeptId(), worker.isOnline());
        if(worker.getIsDriver()) {
            insertLicenses(worker);
        }
        return rowsAffected;
    }

    public void update(final Worker worker) {
        logger.info("DAO: updating object Worker in DB");
        jdbcTemplate.update(UPDATE, worker.getPassportData(), worker.getFullName(), worker.getIsDriver(),
                worker.getPhoneNumber(), worker.getDeptId(), worker.isOnline(), worker.getLogin());
        if(worker.getIsDriver()) {
            //delete invalid licenses
            jdbcTemplate.update(DELETE_LICENSES, worker.getLogin());
            //add valid ones
            insertLicenses(worker);
        }
    }

    public void remove(Worker worker) {
        logger.info("DAO: removing object Worker from DB");
        jdbcTemplate.update(DELETE_LICENSES, worker.getLogin());
        jdbcTemplate.update(DELETE, worker.getLogin());
    }

    public void setOnline(Worker worker){
        logger.info("DAO: set worker online");
        jdbcTemplate.update(SET_ONLINE, true, worker.getLogin());
    }

    public void setOffline(Worker worker){
        logger.info("DAO: set worker offline");
        jdbcTemplate.update(SET_ONLINE, false, worker.getLogin());
    }

    private void insertLicenses(final Worker worker) {
        jdbcTemplate.batchUpdate(INSERT_LICENSES, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Character category = worker.getLicenses().get(i);
                ps.setString(1, worker.getLogin());
                ps.setString(2, String.valueOf(category));
            }

            public int getBatchSize() {
                return worker.getLicenses().size();
            }
        });
    }

    private RowMapper<Worker> mapper = new RowMapper<Worker>() {
        public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
            Worker worker = new Worker();
            worker.setLogin(rs.getString("login"));
            worker.setPassportData(rs.getString("pass_data"));
            worker.setFullName(rs.getString("full_name"));
            worker.setIsDriver(rs.getBoolean("is_driver"));
            worker.setPhoneNumber(rs.getString("phone_num"));
            worker.setDeptId(rs.getInt("dept_id"));
            worker.setOnline(rs.getBoolean("online"));
            try{
                Jdbc4Array array = (Jdbc4Array) rs.getArray("licenses");
                List<Character> licenses = new ArrayList<Character>();
                for(String l : array.toString().substring(1).split(","))
                    licenses.add(l.charAt(0));
               worker.setLicenses(licenses);
            }catch (SQLException e){
                worker.setLicenses(new ArrayList<Character>());
            }
            return worker;
        }
    };

    private ResultSetExtractor<Worker> mapperWithLicenses = new ResultSetExtractor<Worker>() {

        public Worker extractData(ResultSet rs) throws SQLException, DataAccessException {
            Worker worker = null;
            while(rs.next()) {
                if(worker == null) {
                    worker = new Worker();
                    worker.setLogin(rs.getString("login"));
                    worker.setPassportData(rs.getString("pass_data"));
                    worker.setFullName(rs.getString("full_name"));
                    worker.setIsDriver(rs.getBoolean("is_driver"));
                    worker.setPhoneNumber(rs.getString("phone_num"));
                    worker.setDeptId(rs.getInt("dept_id"));
                    worker.setOnline(rs.getBoolean("online"));
                    worker.setLicenses(new ArrayList<Character>());
                }
                String licenseCategory = rs.getString("category");
                if(licenseCategory != null) worker.getLicenses().add(licenseCategory.charAt(0));
            }
            return worker;
        }
    };

}