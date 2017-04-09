package app.pojo;

import app.entity.Client;
import app.entity.Order;

/**
 * Created by Alex_Frankiv on 09.04.2017.
 */
public class ClientOrder {

    private Client client;
    private Order order;

    public ClientOrder() {
    }

    public ClientOrder(Client client, Order order) {
        this.client = client;
        this.order = order;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "client=" + client +
                ", order=" + order +
                '}';
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
