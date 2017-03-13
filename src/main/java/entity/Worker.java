package entity;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class Worker extends User {

    private long numOfContract;
    private String passportNum;
    private String fullName;
    private boolean isDriver;
    private Date dateOfBirth;
    private List<Character> licenses;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Worker{" +
                "numOfContract=" + numOfContract +
                ", passportNum='" + passportNum + '\'' +
                ", fullName='" + fullName + '\'' +
                ", isDriver=" + isDriver +
                ", dateOfBirth=" + dateOfBirth +
                ", licenses=" + licenses +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public Worker(){}

    public long getNumOfContract() {
        return numOfContract;
    }

    public void setNumOfContract(long numOfContract) {
        this.numOfContract = numOfContract;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
