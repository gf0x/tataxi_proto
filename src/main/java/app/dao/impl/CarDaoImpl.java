package app.dao.impl;

import app.dao.CarDao;
import app.entity.Car;
import app.entity.Worker;
import app.pojo.CarStatsPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
//@Cacheable("cars")
public class CarDaoImpl implements CarDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(CarDaoImpl.class.getSimpleName());

    private static final String GET = "SELECT * FROM car WHERE id=?";
    private static final String INSERT = "INSERT INTO car (sign, brand, model, category, seats, max_weight, bought_on," +
            " written_off_on, serviceable, dept_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE car SET sign=?, brand=?, model=?, category=?, seats=?, max_weight=?," +
            "bought_on=?, written_off_on=?, serviceable=?, dept_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM car WHERE id=?";
    private static final String GET_FREE_BY_DISPATCHER = "SELECT * FROM car WHERE id NOT IN (SELECT c_d.car_id FROM car_driver c_d WHERE time_till IS NULL OR now() BETWEEN time_from AND time_till) AND serviceable=TRUE AND dept_id=?";

    private static final String GET_STATS_BY_BRAND_MODEL = "SELECT brand, model, count(DISTINCT id) AS amount FROM car GROUP BY brand, model";

    public Car get(int id) {
        logger.info("DAO: grabbing object Car from DB");
        return jdbcTemplate.queryForObject(GET, mapper, id);
    }

    public int insert(Car car) {
        logger.info("DAO: inserting object Car into DB");
        return jdbcTemplate.update(INSERT, car.getSign(), car.getBrand(), car.getModel(),
                car.getCategory(), car.getSeats(), car.getMaxWeight(), car.getBoughtOn(), car.getWritten_offOn(),
                car.isServiceable(), car.getDeptId());
    }

    public void update(Car car) {
        logger.info("DAO: updating object Car in DB");
        jdbcTemplate.update(UPDATE, car.getSign(), car.getBrand(), car.getModel(),
                car.getCategory(), car.getSeats(), car.getMaxWeight(), car.getBoughtOn(), car.getWritten_offOn(),
                car.isServiceable(), car.getDeptId(), car.getId());
    }

    public void remove(Car car) {
        logger.info("DAO: removing object Car from DB");
        jdbcTemplate.update(DELETE, car.getId());
    }

    public List<Car> getFreeCarsByDispatcher(Worker dispatcher) throws Exception {
        logger.info("DAO: get free cars by dispatcher");
        if(dispatcher.getIsDriver())
            throw new Exception("Access denied: valid for dispatchers only");
        return jdbcTemplate.query(GET_FREE_BY_DISPATCHER, mapper, dispatcher.getDeptId());
    }

    public List<CarStatsPojo> getStatsByBrandModel(){
        return jdbcTemplate.query(GET_STATS_BY_BRAND_MODEL, carStatsPojoMapper);
    }

    private RowMapper<CarStatsPojo> carStatsPojoMapper = new RowMapper<CarStatsPojo>() {
        public CarStatsPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CarStatsPojo(rs.getString("brand"), rs.getString("model"), rs.getInt("amount"));
        }
    };

    private RowMapper<Car> mapper = new RowMapper<Car>() {
        public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setSign(rs.getString("sign"));
            car.setBrand(rs.getString("brand"));
            car.setModel(rs.getString("model"));
            car.setCategory(rs.getString("category").charAt(0));
            car.setSeats(rs.getInt("seats"));
            car.setMaxWeight(rs.getDouble("max_weight"));
            car.setBoughtOn(rs.getDate("bought_on"));
            car.setWritten_offOn(rs.getDate("written_off_on"));
            car.setServiceable(rs.getBoolean("serviceable"));
            car.setDeptId(rs.getInt("dept_id"));
            return car;
        }
    };
}
