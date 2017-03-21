package app.dao.impl;

import app.dao.DepartmentDao;
import app.entity.Department;
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
@Cacheable("departments")
public class DepartmentDaoImpl implements DepartmentDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class.getSimpleName());

    private static final String GET = "SELECT * FROM department WHERE id=?";
    private static final String INSERT = "INSERT INTO department (id, city, address, price_per_km) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE department SET city=?, address=?, price_per_km=? WHERE id=?";
    private static final String DELETE = "DELETE FROM department WHERE id=?";

    public Department get(int id) {
        logger.info("DAO: grabbing object Department from DB");
        return jdbcTemplate.queryForObject(GET, mapper, id);
    }

    public int insert(Department department) {
        logger.info("DAO: inserting object Department into DB");
        return jdbcTemplate.update(INSERT, department.getId(), department.getCity(), department.getAddress(),
                department.getPricePerKm());
    }

    public void update(Department department) {
        logger.info("DAO: updating object Department into DB");
        jdbcTemplate.update(UPDATE, department.getCity(), department.getAddress(), department.getPricePerKm(),
                department.getId());
    }

    public void remove(Department department) {
        logger.info("DAO: removing object Department from DB");
        jdbcTemplate.update(DELETE, department.getId());
    }

    private RowMapper<Department> mapper = new RowMapper<Department>() {
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setCity(rs.getString("city"));
            department.setAddress(rs.getString("address"));
            department.setPricePerKm(rs.getDouble("price_per_km"));
            return department;
        }
    };
}