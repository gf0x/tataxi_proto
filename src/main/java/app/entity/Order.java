package app.entity;

import org.springframework.stereotype.Component;
import app.pojo.Place;

import java.sql.Timestamp;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class Order {

    private int id;
    private Place from;
    private Place to;
    private double distance;
    private double price;
    private boolean isFast;
    private Timestamp startTime;
    private Timestamp finishTime;
    private int feedback;
    private String city;
    //extra
    private int seats;
    private boolean extraLuggage;

    private String dispatcher;
    private String client;
    private int car;

    public Order(){
        //default value
        this.car = -1;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                ", price=" + price +
                ", getIsFast=" + isFast +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", feedback=" + feedback +
                ", city='" + city + '\'' +
                ", seats=" + seats +
                ", extraLuggage=" + extraLuggage +
                ", dispatcher='" + dispatcher + '\'' +
                ", client='" + client + '\'' +
                ", car=" + car +
                '}';
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isExtraLuggage() {
        return extraLuggage;
    }

    public void setExtraLuggage(boolean extraLuggage) {
        this.extraLuggage = extraLuggage;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getIsFast() {
        return isFast;
    }

    public void setIsFast(boolean fast) {
        isFast = fast;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}