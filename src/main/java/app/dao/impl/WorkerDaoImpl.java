package app.dao.impl;

import app.dao.WorkerDao;
import app.entity.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
public class WorkerDaoImpl implements WorkerDao{

    private static Logger logger = LoggerFactory.getLogger(WorkerDaoImpl.class.getSimpleName());

    public Worker get(String login) {
        logger.info("DAO: grabbing object Worker from DB");
        return null;
    }

    public Worker insert(Worker worker) {
        logger.info("DAO: inserting object Worker into DB");
        return null;
    }

    public void update(Worker worker) {
        logger.info("DAO: updating object Worker in DB");
    }

    public void remove(Worker worker) {
        logger.info("DAO: removing object Worker from DB");
    }
}
