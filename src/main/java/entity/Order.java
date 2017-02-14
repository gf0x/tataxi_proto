package entity;

import org.springframework.stereotype.Component;
import pojo.Place;

import java.sql.Time;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class Order {

    private long id;
    private Place from;
    private Place to;
    private double routeLength;
    private double cost;
    private boolean extraSpeed;
    private Time orderedAt;
    private Time finishedAt;
    private String response;

    public Order(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Place getFrom() {
        return from;
    }

    public void setFrom(Place from) {
        this.from = from;
    }

    public Place getTo() {
        return to;
    }

    public void setTo(Place to) {
        this.to = to;
    }

    public double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isExtraSpeed() {
        return extraSpeed;
    }

    public void setExtraSpeed(boolean extraSpeed) {
        this.extraSpeed = extraSpeed;
    }

    public Time getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Time orderedAt) {
        this.orderedAt = orderedAt;
    }

    public Time getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Time finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
