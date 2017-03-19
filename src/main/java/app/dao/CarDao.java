package app.dao;

import app.entity.Car;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface CarDao {

    Car get(int id);
    Car insert(Car car);
    void update(Car car);
    void remove(Car car);
}
