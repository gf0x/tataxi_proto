package app.pojo;

import app.entity.Car;
import app.entity.Order;

/**
 * Created by Alex_Frankiv on 09.04.2017.
 */
public class OrderCar {

    private Order order;
    private Car car;

    @Override
    public String toString() {
        return "OrderCar{" +
                "order=" + order +
                ", car=" + car +
                '}';
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public OrderCar() {

    }

    public OrderCar(Order order, Car car) {

        this.order = order;
        this.car = car;
    }
}
