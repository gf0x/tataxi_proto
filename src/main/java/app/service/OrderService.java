package app.service;

import app.entity.Car;
import app.entity.Order;
import app.entity.Worker;
import app.pojo.ClientOrder;

import java.util.List;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface OrderService {
    Order get(int id);
    int insert(Order order);
    void update(Order order);
    void remove(Order order);
    List<ClientOrder> getAwaitingForDispatcher(Worker dispatcher);
    void accept(Order order, Car car, Worker dispatcher);
    void decline(Order order, Worker dispatcher);
    ClientOrder getCurrentOrderDriver(Worker driver);
    void finishOrder(Order order);
}
