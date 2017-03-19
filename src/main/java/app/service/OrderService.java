package app.service;

import app.entity.Order;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface OrderService {
    Order get(int id);
    Order insert(Order order);
    void update(Order order);
    void remove(Order order);
}
