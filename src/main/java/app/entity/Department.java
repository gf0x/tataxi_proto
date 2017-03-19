package app.entity;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class Department {

    private int id;
    private String address;
    private String city;
    private List<String> phoneNums;
    private double pricePerKm;

    public Department() {}

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phoneNums=" + phoneNums +
                ", pricePerKm=" + pricePerKm +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getPhoneNums() {
        return phoneNums;
    }

    public void setPhoneNums(List<String> phoneNums) {
        this.phoneNums = phoneNums;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }
}
