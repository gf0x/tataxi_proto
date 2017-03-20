package app.service.impl;

import app.dao.WorkerDao;
import app.entity.Worker;
import app.service.WorkerService;
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
public class WorkerServiceImpl implements WorkerService{

    @Autowired
    private WorkerDao workerDao;

    private static Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class.getSimpleName());

    public Worker get(String login) {
        logger.info("SERVICE: grabbing object Worker from DB");
        return workerDao.get(login);
    }

    public int insert(Worker worker) {
        logger.info("SERVICE: inserting object Worker into DB");
        return workerDao.insert(worker);
    }

    public void update(Worker worker) {
        logger.info("SERVICE: updating object Worker in DB");
        workerDao.update(worker);
    }

    public void remove(Worker worker) {
        logger.info("SERVICE: removing object Worker from DB");
        workerDao.remove(worker);
    }
}
