package ua.skylab.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ua.skylab.dao.CrudDao;
import ua.skylab.service.CrudService;

public abstract class CrudServiceImpl<E, N extends Number> implements CrudService<E, N> {
	
	@Autowired
	private CrudDao<E, N> crudDao;
	
	public void save(E entity) {
		crudDao.save(entity);
	}

	public void update(E entity) {
		crudDao.update(entity);
	}
	@Transactional
	public void delete(N id) {
		crudDao.delete(findById(id));
	}

	public E findById(N id) {
		return crudDao.findOneById(id);
	}

	public List<E> getAll() {
		return crudDao.getAll();
	}
	
	public Class<E> getEntityClass(){
		return crudDao.getEntityClass();
	}
}
