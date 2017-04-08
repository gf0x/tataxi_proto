package app.service.impl;

import app.dao.WorkerDao;
import app.entity.Worker;
import app.service.UserService;
import app.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerDao workerDao;
    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class.getSimpleName());
    private static final int ERROR_CODE = -1;

    public Worker get(String login) {
        logger.info("SERVICE: grabbing object Worker from DB");
        return workerDao.get(login);
    }

    public int insert(Worker worker) {
        logger.info("SERVICE: inserting object Worker into DB");
        if (userService.insert(worker) > 0)
            return workerDao.insert(worker);
        else
            return ERROR_CODE;
    }

    public void update(Worker worker) {
        logger.info("SERVICE: updating object Worker in DB");
        update(worker, false);
    }

    public void update(Worker worker, boolean force){
        if (force)
            userService.update(worker);
        workerDao.update(worker);
    }

    public List<Worker> getFreeByDispatcher(Worker dispatcher) throws Exception {
        logger.info("SERVICE: get free drivers by dispatcher");
        return workerDao.getFreeByDispatcher(dispatcher);
    }

    public void remove(Worker worker) {
        logger.info("SERVICE: removing object Worker from DB");
        workerDao.remove(worker);
    }
}
