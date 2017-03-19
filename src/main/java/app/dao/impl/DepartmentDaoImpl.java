package app.dao.impl;

import app.dao.DepartmentDao;
import app.entity.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex_Frankiv on 19.03.2017.
 */
@Repository
public class DepartmentDaoImpl implements DepartmentDao{

    private static Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class.getSimpleName());

    public Department get(int id) {
        logger.info("DAO: grabbing object Department from DB");
        return null;
    }

    public Department insert(Department department) {
        logger.info("DAO: inserting object Department into DB");
        return null;
    }

    public void update(Department department) {
        logger.info("DAO: updating object Department into DB");
    }

    public void remove(Department department) {
        logger.info("DAO: removing object Department from DB");
    }
}
