package app.dao.impl;

import app.dao.ClientDao;
import app.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
public class ClientDaoImpl implements ClientDao{

    private static Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class.getSimpleName());

    public Client get(String login) {
        logger.info("DAO: grabbing object Client from DB");
        return null;
    }

    public Client insert(Client client) {
        logger.info("DAO: inserting object Client into DB");
        return null;
    }

    public void update(Client client) {
        logger.info("DAO: updating object Client in DB");
    }

    public void remove(Client client) {
        logger.info("DAO: removing object Client from DB");
    }
}
