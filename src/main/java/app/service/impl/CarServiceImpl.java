package app.service.impl;

import app.dao.CarDao;
import app.entity.Car;
import app.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService{

    @Autowired
    private CarDao carDao;

    private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class.getSimpleName());

    public Car get(int id) {
        logger.info("SERVICE: grabbing object Car from DB...");
        return carDao.get(id);
    }

    public Car insert(Car car) {
        logger.info("SERVICE: inserting object Car into DB...");
        return carDao.insert(car);
    }

    public void update(Car car) {
        logger.info("SERVICE: updating object Car in DB...");
        carDao.update(car);
    }

    public void remove(Car car) {
        logger.info("SERVICE: removing object Car from DB...");
        carDao.remove(car);
    }
}
