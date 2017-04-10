package app.dao.impl;

import app.dao.OrderDao;
import app.entity.Car;
import app.entity.Client;
import app.entity.Order;
import app.entity.Worker;
import app.pojo.ClientOrder;
import app.pojo.Place;
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
//@Cacheable("orders")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET = "SELECT * FROM \"order\" WHERE id=?";
    private static final String INSERT = "INSERT INTO \"order\" (from_lat, from_lng, to_lat, to_lng, distance, price, " +
            "is_fast, start_time, finish_time, feedback, car_id, worker_login, client_login, city, from_address, to_address, seats, extra_luggage)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE \"order\" SET from_lat=?, from_lng=?, to_lat=?, to_lng=?, distance=?," +
            "price=?, is_fast=?, start_time=?, finish_time=?, feedback=?, car_id=?, worker_login=?, client_login=?, " +
            "city=?, from_address=?, to_address=?, seats=?, extra_luggage=? WHERE id=?";
    private static final String DELETE = "DELETE FROM order WHERE id=?";
    private static final String GET_AWAITING = "SELECT * FROM \"order\" o INNER JOIN client c ON o.client_login=c.login WHERE o.worker_login IS NULL AND o.car_id ISNULL AND o.city IN (SELECT d.city FROM department d WHERE d.id=?)";
    private static final String ACCEPT = "UPDATE \"order\" SET car_id=?, worker_login=? WHERE id=?";
    private static final String DECLINE = "UPDATE \"order\" SET worker_login=? WHERE id=?";

    private static final String GET_CURRENT_FOR_DRIVER = "SELECT * FROM \"order\" o INNER JOIN client c ON o.client_login=c.login WHERE o.finish_time ISNULL AND o.car_id IN (SELECT c_d.car_id FROM car_driver c_d WHERE c_d.time_till ISNULL AND c_d.worker_login=?)";
    private static final String FINISH_ORDER = "UPDATE \"order\" SET finish_time=now() WHERE id=?";
    private static final String GET_CLIENT_ORDER_BY_ID = "SELECT * FROM \"order\" o INNER JOIN client c ON o.client_login=c.login WHERE o.id=?";

    private static Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class.getSimpleName());

    public Order get(int id) {
        logger.info("DAO: grabbing object Order from DB");
        return jdbcTemplate.queryForObject(GET, mapper, id);
    }

    public int insert(Order order) {
        logger.info("DAO: inserting object Order into DB");
        return jdbcTemplate.update(INSERT, order.getFrom().getLat(), order.getFrom().getLng(),
                order.getTo().getLat(), order.getTo().getLng(), order.getDistance(), order.getPrice(), order.getIsFast(),
                order.getStartTime(), order.getFinishTime(), order.getFeedback(), (order.getCar() >= 0) ? order.getCar() : null, order.getDispatcher(),
                order.getClient(), order.getCity(), order.getFrom().getAddress(), order.getTo().getAddress(), order.getSeats(), order.isExtraLuggage());
    }

    public void update(Order order) {
        logger.info("DAO: updating object Order in DB");
        jdbcTemplate.update(UPDATE, order.getFrom().getLat(), order.getFrom().getLng(),
                order.getTo().getLat(), order.getTo().getLng(), order.getDistance(), order.getPrice(), order.getIsFast(),
                order.getStartTime(), order.getFinishTime(), order.getFeedback(), (order.getCar() >= 0) ? order.getCar() : null, order.getDispatcher(),
                order.getClient(), order.getCity(), order.getFrom().getAddress(), order.getTo().getAddress(), order.getSeats(),
                order.isExtraLuggage(), order.getId());
    }

    public void remove(Order order) {
        logger.info("DAO: removing object Order from DB");
        jdbcTemplate.update(DELETE, order.getId());
    }

    public List<ClientOrder> getAwaitingForDispatcher(Worker dispatcher) {
        logger.info("DAO: getting awaiting orders");
        return jdbcTemplate.query(GET_AWAITING, clientOrderMapper, dispatcher.getDeptId());
    }

    public void accept(Order order, Car car, Worker dispatcher) {
        logger.info("DAO: accepting order");
        jdbcTemplate.update(ACCEPT, car.getId(), dispatcher.getLogin(), order.getId());
    }

    public void decline(Order order, Worker dispatcher) {
        logger.info("DAO: declining order");
        jdbcTemplate.update(DECLINE, dispatcher.getLogin(), order.getId());
    }

    public ClientOrder getCurrentOrderDriver(Worker driver){
        logger.info("DAO: getting current order for driver");
        List<ClientOrder> resp = jdbcTemplate.query(GET_CURRENT_FOR_DRIVER, clientOrderMapper, driver.getLogin());
        return (resp.isEmpty())?null:resp.get(0);
    }

    public void finishOrder(Order order){
        jdbcTemplate.update(FINISH_ORDER, order.getId());
    }

    public ClientOrder getClientOrderById(int id){
        List<ClientOrder> resp = jdbcTemplate.query(GET_CLIENT_ORDER_BY_ID, clientOrderMapper, id);
        return (resp.isEmpty())?null:resp.get(0);
    }

    private RowMapper<ClientOrder> clientOrderMapper = new RowMapper<ClientOrder>() {
        public ClientOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            Place from = new Place();
            from.setLat(rs.getDouble("from_lat"));
            from.setLng(rs.getDouble("from_lng"));
            from.setAddress(rs.getString("from_address"));
            order.setFrom(from);
            Place to = new Place();
            to.setLat(rs.getDouble("to_lat"));
            to.setLng(rs.getDouble("to_lng"));
            to.setAddress(rs.getString("to_address"));
            order.setTo(to);
            order.setDistance(rs.getDouble("distance"));
            order.setPrice(rs.getDouble("price"));
            order.setIsFast(rs.getBoolean("is_fast"));
            order.setStartTime(rs.getTimestamp("start_time"));
            order.setFinishTime(rs.getTimestamp("finish_time"));
            order.setFeedback(rs.getInt("feedback"));
            order.setCar(rs.getInt("car_id"));
            order.setDispatcher(rs.getString("worker_login"));
            order.setClient(rs.getString("client_login"));
            order.setCity(rs.getString("city"));
            order.setExtraLuggage(rs.getBoolean("extra_luggage"));
            order.setSeats(rs.getInt("seats"));
            Client client = new Client();
            client.setLogin(rs.getString("login"));
            client.setRealName(rs.getString("real_name"));
            client.setEmail(rs.getString("email"));
            client.setHomeAddress(rs.getString("home_address"));
            client.setPhoneNumber(rs.getString("phone_num"));
            return new ClientOrder(client, order);
        }
    };

    private RowMapper<Order> mapper = new RowMapper<Order>() {
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            Place from = new Place();
            from.setLat(rs.getDouble("from_lat"));
            from.setLng(rs.getDouble("from_lng"));
            from.setAddress(rs.getString("from_address"));
            order.setFrom(from);
            Place to = new Place();
            to.setLat(rs.getDouble("to_lat"));
            to.setLng(rs.getDouble("to_lng"));
            to.setAddress(rs.getString("to_address"));
            order.setTo(to);
            order.setDistance(rs.getDouble("distance"));
            order.setPrice(rs.getDouble("price"));
            order.setIsFast(rs.getBoolean("is_fast"));
            order.setStartTime(rs.getTimestamp("start_time"));
            order.setFinishTime(rs.getTimestamp("finish_time"));
            order.setFeedback(rs.getInt("feedback"));
            order.setCar(rs.getInt("car_id"));
            order.setDispatcher(rs.getString("worker_login"));
            order.setClient(rs.getString("client_login"));
            order.setCity(rs.getString("city"));
            order.setExtraLuggage(rs.getBoolean("extra_luggage"));
            order.setSeats(rs.getInt("seats"));
            return order;
        }
    };
}