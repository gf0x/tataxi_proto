package app.dao;

import app.entity.Worker;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface WorkerDao {

    Worker get(String login);
    int insert(Worker worker);
    void update(Worker worker);
    void remove(Worker worker);
}
