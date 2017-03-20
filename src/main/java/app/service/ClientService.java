package app.service;

import app.entity.Client;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface ClientService {

    Client get(String login);
    int insert(Client client);
    void update(Client client);
    void remove(Client client);
}
