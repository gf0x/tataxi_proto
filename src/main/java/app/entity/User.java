package app.entity;

import org.springframework.stereotype.Component;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class User {

    private String login;

    @Override
    public String toString() {
        return "UserDao{" +
                "login='" + login + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User(){}
}
