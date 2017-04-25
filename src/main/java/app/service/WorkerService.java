package app.service;

import app.entity.Worker;

import java.util.List;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface WorkerService {

    Worker get(String login);
    int insert(Worker worker);
    void update(Worker worker);
    void remove(Worker worker);
    void update(Worker worker, boolean force);
    List<Worker> getFreeByDispatcher(Worker dispatcher) throws Exception;
    void setOnline(Worker worker);
    void setOffline(Worker worker);
    Worker getDispatcherByOrderId(int id);
    List<Worker> getDriversWhoTriedAllCarsInTheirDept();
    List<Worker> getAll();
}
