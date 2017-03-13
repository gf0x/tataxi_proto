package entity;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class Department {

    private long id;
    private String address;
    private List<String> phoneNums;

    public Department() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhoneNums() {
        return phoneNums;
    }

    public void setPhoneNums(List<String> phoneNums) {
        this.phoneNums = phoneNums;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phoneNums=" + phoneNums +
                '}';
    }
}
