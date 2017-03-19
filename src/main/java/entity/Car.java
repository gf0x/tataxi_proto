package entity;

import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class Car {

    private int id;
    //Державний номерний знак
    private String sign;
    private String brand;
    private String model;
    private boolean isCargo;
    private int seats;
    private double maxWeight;
    private Date boughtOn;
    private Date written_offOn;
    private boolean serviceable;
    private int deptId;

    public Car() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
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

    public boolean isServiceable() {
        return serviceable;
    }

    public void setServiceable(boolean serviceable) {
        this.serviceable = serviceable;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", sign='" + sign + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", isCargo=" + isCargo +
                ", seats=" + seats +
                ", maxWeight=" + maxWeight +
                ", boughtOn=" + boughtOn +
                ", written_offOn=" + written_offOn +
                ", serviceable=" + serviceable +
                ", deptId=" + deptId +
                '}';
    }
}
