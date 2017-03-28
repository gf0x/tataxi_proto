package app.entity;

import org.springframework.stereotype.Component;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Component
public class User {

    private String login;
    private String pswd;
    private String authRole;
    private int enabled;

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getAuthRole() {
        return authRole;
    }

    public void setAuthRole(String authRole) {
        this.authRole = authRole;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public User(String login, String pswd, String authRole, int enabled) {
        this.login = login;
        this.pswd = pswd;
        this.authRole = authRole;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +

                "login='" + login + '\'' +
                ", pswd='" + pswd + '\'' +
                ", authRole='" + authRole + '\'' +
                ", enabled=" + enabled +
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
