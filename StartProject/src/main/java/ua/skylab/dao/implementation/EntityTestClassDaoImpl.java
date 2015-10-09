package ua.skylab.dao.implementation;

import org.springframework.stereotype.Repository;

import ua.skylab.dao.EntityTestClassDao;
import ua.skylab.entity.EntityTestClass;

@Repository
public class EntityTestClassDaoImpl extends PageAndCrudDaoImpl<EntityTestClass, Integer> implements EntityTestClassDao{

}
