package app.service;

import app.entity.Department;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface DepartmentService {
    Department get(int id);
    Department insert(Department department);
    void update(Department department);
    void remove(Department department);

}
