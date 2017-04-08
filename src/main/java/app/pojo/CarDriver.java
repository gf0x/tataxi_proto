package app.pojo;

import app.entity.Car;
import app.entity.Worker;

/**
 * Created by Alex_Frankiv on 08.04.2017.
 */
public class CarDriver {

    private Car car;
    private Worker driver;

    @Override
    public String toString() {
        return "CarDriver{" +
                "car=" + car +
                ", driver=" + driver +
                '}';
    }

    public CarDriver() {
    }

    public CarDriver(Car car, Worker driver) {
        this.car = car;
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Worker getDriver() {
        return driver;
    }

    public void setDriver(Worker driver) {
        this.driver = driver;
    }
}
