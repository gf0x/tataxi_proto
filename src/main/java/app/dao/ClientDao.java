package app.dao;


import app.entity.Client;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface ClientDao {
    Client get(String login);
    Client insert(Client client);
    void update(Client client);
    void remove(Client client);
}
