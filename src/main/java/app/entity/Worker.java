package app.entity;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class Worker extends app.entity.User {

    private static final String WORKER_ROLE = "ROLE_WORKER";
    private static final int ENABLED = 1;

    private String login;
    private String passportData;
    private String fullName;
    private boolean isDriver;
    private List<Character> licenses;
    private String phoneNumber;
    private int deptId;
    private boolean online;

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
                ", online=" + online +
                '}';
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public Worker(){
        this.setAuthRole(WORKER_ROLE);
        this.setEnabled(ENABLED);
    }

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

    public boolean getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(boolean driver) {
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
