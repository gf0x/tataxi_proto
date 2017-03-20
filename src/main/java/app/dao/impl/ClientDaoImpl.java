package app.dao.impl;

import app.dao.ClientDao;
import app.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
public class ClientDaoImpl implements ClientDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class.getSimpleName());

    private static final String GET = "SELECT * FROM client WHERE login=?";
    private static final String INSERT = "INSERT INTO client (login, first_name, second_name, last_name, home_address, " +
            "phone_num) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE client SET first_name=?, second_name=?, last_name=?, home_address=?," +
            "phone_num=? WHERE login=?";
    private static final String DELETE = "DELETE FROM client WHERE login=?";

    public Client get(String login) {
        logger.info("DAO: grabbing object Client from DB");
        return jdbcTemplate.queryForObject(GET, mapper, login);
    }

    public int insert(Client client) {
        logger.info("DAO: inserting object Client into DB");
        return jdbcTemplate.update(INSERT, client.getLogin(), client.getFirstName(), client.getSecondName(),
                client.getLastName(), client.getHomeAddress(), client.getPhoneNumber());
    }

    public void update(Client client) {
        logger.info("DAO: updating object Client in DB");
        jdbcTemplate.update(UPDATE, client.getFirstName(), client.getSecondName(),
                client.getLastName(), client.getHomeAddress(), client.getPhoneNumber(), client.getLogin());
    }

    public void remove(Client client) {
        logger.info("DAO: removing object Client from DB");
        jdbcTemplate.update(DELETE, client.getLogin());
    }

    private RowMapper<Client> mapper = new RowMapper<Client>() {
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setLogin(rs.getString("login"));
            client.setFirstName(rs.getString("first_name"));
            client.setSecondName(rs.getString("second_name"));
            client.setLastName(rs.getString("last_name"));
            client.setHomeAddress(rs.getString("home_address"));
            client.setPhoneNumber(rs.getString("phone_num"));
            return client;
        }
    };
}
