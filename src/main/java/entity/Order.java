package entity;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Component;
import pojo.Place;

import java.sql.Time;

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
    private Time startTime;
    private Time finishTime;
    private String feedback;
    private String city;

    public Order(){}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                ", price=" + price +
                ", isFast=" + isFast +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", feedback='" + feedback + '\'' +
                ", city='" + city + '\'' +
                '}';
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

    public boolean isFast() {
        return isFast;
    }

    public void setFast(boolean fast) {
        isFast = fast;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Time finishTime) {
        this.finishTime = finishTime;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}