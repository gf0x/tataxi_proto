package app.service.impl;

import app.dao.CarDriverDao;
import app.entity.Car;
import app.entity.Worker;
import app.pojo.CarDriver;
import app.service.CarDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Alex_Frankiv on 08.04.2017.
 */
@Service
@Transactional
public class CarDriverServiceImpl implements CarDriverService {

    @Autowired
    private CarDriverDao carDriverDao;

    public List<CarDriver> getCarDriversForDispatcher(Worker worker) {
        return carDriverDao.getCarDriversForDispatcher(worker);
    }

    public int createCarDriverPair(Car car, Worker driver, Worker dispatcher) {
        return carDriverDao.createCarDriverPair(car, driver, dispatcher);
    }

    public int cancelCarDriverPair(Car car, Worker driver, Worker dispatcher) {
        return carDriverDao.cancelCarDriverPair(car, driver, dispatcher);
    }
}
