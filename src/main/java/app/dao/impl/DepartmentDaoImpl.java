package app.dao.impl;

import app.dao.DepartmentDao;
import app.entity.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
//@Cacheable("departments")
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class.getSimpleName());

    private static final String GET = "SELECT d.id, d.city, d.address, d.price_per_km, d_p.phone_number FROM department d LEFT JOIN department_phones d_p ON d.id=d_p.dept_id WHERE id=?";
    private static final String INSERT = "INSERT INTO department (city, address, price_per_km) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE department SET city=?, address=?, price_per_km=? WHERE id=?";
    private static final String DELETE = "DELETE FROM department WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM department ORDER BY city ASC";
    private static final String GET_ALL_BASIC = "SELECT id, city FROM department ORDER BY city ASC";

    private static final String INSERT_PHONES = "INSERT INTO department_phones (dept_id, phone_number) VALUES (?,?)";
    private static final String DELETE_PHONES = "DELETE FROM department_phones WHERE dept_id=?";

    public Department get(int id) {
        logger.info("DAO: grabbing object Department from DB id: " + id);
        return jdbcTemplate.query(GET, fullMapper, id);
    }

    public int insert(final Department department) {
        logger.info("DAO: inserting object Department into DB: " + department);
        final PreparedStatementCreator psc = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                final PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,department.getCity());
                ps.setString(2, department.getAddress());
                ps.setDouble(3, department.getPricePerKm());
                return ps;
            }
        };
        final KeyHolder holder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(psc, holder);
        department.setId((Integer) holder.getKeyList().get(0).get("id"));
        insertPhones(department);
        return rowsAffected;
    }

    public void update(Department department) {
        logger.info("DAO: updating object Department into DB: " + department);
        jdbcTemplate.update(UPDATE, department.getCity(), department.getAddress(), department.getPricePerKm(),
                department.getId());
        jdbcTemplate.update(DELETE_PHONES, department.getId());
        insertPhones(department);
    }

    public void remove(Department department) {
        logger.info("DAO: removing object Department from DB: " + department);
        jdbcTemplate.update(DELETE_PHONES, department.getId());
        jdbcTemplate.update(DELETE, department.getId());
    }

    public List<Department> getAll(boolean basic) {
        logger.info("DAO: getting all objects Department from DB");
        if (basic)
            return jdbcTemplate.query(GET_ALL_BASIC, mapper);
        else
            return jdbcTemplate.query(GET_ALL, mapper);
    }

    //for inserting
    private void insertPhones(final Department department) {
        jdbcTemplate.batchUpdate(INSERT_PHONES, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, department.getId());
                ps.setString(2, department.getPhoneNums().get(i));
            }
            public int getBatchSize() {
                return department.getPhoneNums().size();
            }
        });
    }

    private RowMapper<Department> mapper = new RowMapper<Department>() {
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setCity(rs.getString("city"));
            try {
                department.setAddress(rs.getString("address"));
            }catch (SQLException e){
                department.setAddress(null);
            }
            try {
                department.setPricePerKm(rs.getDouble("price_per_km"));
            }catch (SQLException e){
                department.setPricePerKm(0);
            }
            return department;
        }
    };

    private ResultSetExtractor<Department> fullMapper = new ResultSetExtractor<Department>() {
        public Department extractData(ResultSet rs) throws SQLException, DataAccessException {
            Department department = null;
            while (rs.next()){
                if(department==null){
                    department = new Department();
                    department.setId(rs.getInt("id"));
                    department.setCity(rs.getString("city"));
                    try {
                        department.setAddress(rs.getString("address"));
                    }catch (SQLException e){
                        department.setAddress(null);
                    }
                    try {
                        department.setPricePerKm(rs.getDouble("price_per_km"));
                    }catch (SQLException e){
                        department.setPricePerKm(0);
                    }
                    department.setPhoneNums(new ArrayList<String>());
                }
                String phoneNum = rs.getString("phone_number");
                if (phoneNum != null) department.getPhoneNums().add(phoneNum);
            }
            return department;
        }
    };
}