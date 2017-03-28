package app.entity;


/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
public class Client extends User {

    private String login;
    private String realName;
    private String email;
    private String homeAddress;
    private String phoneNumber;

    public Client(){}

    public Client(String login, String realName, String email, String homeAddress, String phoneNumber) {
        this.login = login;
        this.realName = realName;
        this.email = email;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
