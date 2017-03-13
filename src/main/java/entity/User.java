package entity;

import org.springframework.stereotype.Component;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class User {

    private String username;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    User(){}
}
