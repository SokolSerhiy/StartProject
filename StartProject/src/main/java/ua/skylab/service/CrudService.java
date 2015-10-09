package ua.skylab.service;

import java.util.List;

public interface CrudService<E, N extends Number>{

	void save(E entity);

	void update(E entity);

	void delete(N id);

	E findById(N id);

	List<E> getAll();
	
	Class<E> getEntityClass();
}
