package app.dao.impl;

import app.dao.CarDriverDao;
import app.entity.Car;
import app.entity.Worker;
import app.pojo.CarDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alex_Frankiv on 08.04.2017.
 */
@Repository
public class CarDriverDaoImpl implements CarDriverDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_BY_DEPT = "SELECT c.id, c.sign, c.brand, c.model, c.serviceable, w.login, w.full_name, w.dept_id FROM car c INNER JOIN car_driver c_d ON c.id = c_d.car_id INNER JOIN worker w ON c_d.worker_login = w.login WHERE w.dept_id=?";
    private static final String GET_VALID_BY_DEPT = "SELECT c.id, c.sign, c.brand, c.model, c.serviceable, w.login, w.full_name, w.dept_id FROM car c INNER JOIN car_driver c_d ON c.id = c_d.car_id INNER JOIN worker w ON c_d.worker_login = w.login\n" +
            "WHERE (c_d.time_till IS NULL OR (c_d.time_till > now())) AND w.dept_id=?";
    private static final String GET_VALID_BY_DEPT_AND_ONLINE = "SELECT c.id, c.sign, c.brand, c.model, c.serviceable, w.login, w.full_name, w.dept_id FROM car c INNER JOIN car_driver c_d ON c.id = c_d.car_id INNER JOIN worker w ON c_d.worker_login = w.login\n" +
            "WHERE (c_d.time_till IS NULL OR (c_d.time_till > now())) AND w.online=TRUE AND w.dept_id=?";
    private static final String CANCEL = "UPDATE car_driver SET time_till=now() WHERE time_till IS NULL AND car_id=? AND worker_login=?";
    private static final String CREATE = "INSERT INTO car_driver (worker_login, car_id, time_from, time_till) VALUES (?,?,now(),NULL)";

    public List<CarDriver> getCarDriversForDispatcher(Worker worker) {
        return jdbcTemplate.query(GET_VALID_BY_DEPT, mapper, worker.getDeptId());
    }

    public int createCarDriverPair(Car car, Worker driver, Worker dispatcher) throws Exception {
        if (car.getDeptId() != dispatcher.getDeptId() || driver.getDeptId() != dispatcher.getDeptId())
            throw new Exception("Access denied: Department ids mismatch!");
        return jdbcTemplate.update(CREATE, driver.getLogin(), car.getId());
    }

    public int cancelCarDriverPair(Car car, Worker driver, Worker dispatcher) throws Exception {
        if (car.getDeptId() != dispatcher.getDeptId() || driver.getDeptId() != dispatcher.getDeptId())
            throw new Exception("Access denied: Department ids mismatch!");
        return jdbcTemplate.update(CANCEL, car.getId(), driver.getLogin());
    }

    private RowMapper<CarDriver> mapper = new RowMapper<CarDriver>() {
        public CarDriver mapRow(ResultSet rs, int rowNum) throws SQLException {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setSign(rs.getString("sign"));
            car.setBrand(rs.getString("brand"));
            car.setModel(rs.getString("model"));
            car.setDeptId(rs.getInt("dept_id"));
            car.setServiceable(rs.getBoolean("serviceable"));
            Worker driver = new Worker();
            driver.setIsDriver(true);
            driver.setLogin(rs.getString("login"));
            driver.setFullName(rs.getString("full_name"));
            driver.setDeptId(rs.getInt("dept_id"));
            return new CarDriver(car, driver);
        }
    };
}
