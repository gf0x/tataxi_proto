package app.entity;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class Worker extends app.entity.User {

    private String login;
    private String passportData;
    private String fullName;
    private boolean isDriver;
    private List<Character> licenses;
    private String phoneNumber;
    private int deptId;

    @Override
    public String toString() {
        return "Worker{" +
                "login='" + login + '\'' +
                ", passportData='" + passportData + '\'' +
                ", fullName='" + fullName + '\'' +
                ", isDriver=" + isDriver +
                ", licenses=" + licenses +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", deptId=" + deptId +
                '}';
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public Worker(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public List<Character> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<Character> licenses) {
        this.licenses = licenses;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
