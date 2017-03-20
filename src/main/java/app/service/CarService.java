package app.service;

import app.entity.Car;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface CarService {

    Car get(int id);
    int insert(Car car);
    void update(Car car);
    void remove(Car car);
}
