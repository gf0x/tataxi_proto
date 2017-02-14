package entity;

import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class Car {

    private long id;
    //Державний номерний знак
    private String sign;
    private String brand;
    private String model;
    private boolean isCargo;
    private long numOfSeats;
    private double maxCargo;
    private Date boughtOn;
    private Date written_offOn;
    private boolean isBroken;

    public Car() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isCargo() {
        return isCargo;
    }

    public void setCargo(boolean cargo) {
        isCargo = cargo;
    }

    public long getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(long numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public double getMaxCargo() {
        return maxCargo;
    }

    public void setMaxCargo(double maxCargo) {
        this.maxCargo = maxCargo;
    }

    public Date getBoughtOn() {
        return boughtOn;
    }

    public void setBoughtOn(Date boughtOn) {
        this.boughtOn = boughtOn;
    }

    public Date getWritten_offOn() {
        return written_offOn;
    }

    public void setWritten_offOn(Date written_offOn) {
        this.written_offOn = written_offOn;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }
}
