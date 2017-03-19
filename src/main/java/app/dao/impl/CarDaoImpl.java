package app.dao.impl;

import app.dao.CarDao;
import app.entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
public class CarDaoImpl implements CarDao{

    private static Logger logger = LoggerFactory.getLogger(CarDaoImpl.class.getSimpleName());

    public Car get(int id) {
        logger.info("DAO: grabbing object Car from DB");
        return null;
    }

    public Car insert(Car car) {
        logger.info("DAO: inserting object Car into DB");
        return null;
    }

    public void update(Car car) {
        logger.info("DAO: updating object Car in DB");
    }

    public void remove(Car car) {
        logger.info("DAO: removing object Car from DB");
    }
}
