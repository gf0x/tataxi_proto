package app.service.impl;

import app.dao.DepartmentDao;
import app.entity.Department;
import app.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    private static Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class.getSimpleName());

    public Department get(int id) {
        logger.info("SERVICE: grabbing object Department from DB");
        return departmentDao.get(id);
    }

    public int insert(Department department) {
        logger.info("SERVICE: inserting object Department into DB");
        return departmentDao.insert(department);
    }

    public void update(Department department) {
        logger.info("SERVICE: updating object Department in DB");
        departmentDao.update(department);
    }

    public void remove(Department department) {
        logger.info("SERVICE: removing object Department from DB");
        departmentDao.remove(department);
    }
}
