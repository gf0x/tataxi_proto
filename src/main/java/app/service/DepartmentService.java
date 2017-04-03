package app.service;

import app.entity.Department;

import java.util.List;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
public interface DepartmentService {
    Department get(int id);
    int insert(Department department);
    void update(Department department);
    void remove(Department department);
    List<Department> getAll(boolean basic);
}
