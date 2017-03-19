package app.dao.impl;

import app.dao.OrderDao;
import app.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
public class OrderDaoImpl implements OrderDao{

    private static Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class.getSimpleName());

    public Order get(int id){
        logger.info("DAO: grabbing object Order from DB");
        return null;
    }

    public Order insert(Order order) {
        logger.info("DAO: inserting object Order into DB");
        return null;
    }

    public void update(Order order) {
        logger.info("DAO: updating object Order in DB");
    }

    public void remove(Order order) {
        logger.info("DAO: removing object Order from DB");
    }
}
