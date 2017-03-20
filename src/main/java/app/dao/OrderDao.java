package app.dao;

import app.entity.Order;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface OrderDao {

    Order get(int id);
    int insert(Order order);
    void update(Order order);
    void remove(Order order);
}
