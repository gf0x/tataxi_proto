package app.service;

import app.entity.Worker;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface WorkerService {

    Worker get(String login);
    Worker insert(Worker worker);
    void update(Worker worker);
    void remove(Worker worker);
}
