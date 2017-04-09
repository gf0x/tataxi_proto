package app.service.impl;

import app.dao.OrderDao;
import app.entity.Car;
import app.entity.Order;
import app.entity.Worker;
import app.pojo.ClientOrder;
import app.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class.getSimpleName());

    public Order get(int id) {
        logger.info("SERVICE: grabbing object OrderDaoImpl from DB");
        return orderDao.get(id);
    }

    public int insert(Order order) {
        logger.info("SERVICE: inserting object OrderDaoImpl into DB");
        return orderDao.insert(order);
    }

    public void update(Order order) {
        logger.info("SERVICE: updating object OrderDaoImpl in DB");
        orderDao.update(order);
    }

    public void remove(Order order) {
        logger.info("SERVICE: removing object OrderDaoImpl from DB");
        orderDao.remove(order);
    }

    public List<ClientOrder> getAwaitingForDispatcher(Worker dispatcher) {
        logger.info("SERVICE: getting awaiting orders");
        return orderDao.getAwaitingForDispatcher(dispatcher);
    }

    public void accept(Order order, Car car, Worker dispatcher) {
        logger.info("SERVICE: accept order");
        orderDao.accept(order, car, dispatcher);
    }

    public void decline(Order order, Worker dispatcher) {
        logger.info("SERVICE: decline order");
        orderDao.decline(order, dispatcher);
    }
}
