package app.service.impl;

import app.dao.ClientDao;
import app.entity.Client;
import app.service.ClientService;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class.getSimpleName());
    private static final int ERROR_CODE = -1;

    public Client get(String login) {
        logger.info("SERVICE: grabbing object Client from DB");
        return clientDao.get(login);
    }

    public int insert(Client client) {
        logger.info("SERVICE: inserting object Client into DB");
        if (userService.insert(client) > 0)
            return clientDao.insert(client);
        else
            return ERROR_CODE;
    }

    public void update(Client client) {
        logger.info("SERVICE: updating object Client in DB");
        clientDao.update(client);
    }

    public void remove(Client client) {
        logger.info("SERVICE: removing object Client from DB");
        clientDao.remove(client);
    }
}
