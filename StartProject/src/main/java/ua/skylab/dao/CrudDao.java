package ua.skylab.dao;

import java.util.List;

public interface CrudDao<E, N extends Number> {

	void save(E entity);

	void update(E entity);

	List<E> getAll();

	void delete(E entity);

	E findOneById(N id);

	Class<E> getEntityClass();
}
