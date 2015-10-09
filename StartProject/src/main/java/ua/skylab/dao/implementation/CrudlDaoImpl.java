package ua.skylab.dao.implementation;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Transactional;

import ua.skylab.dao.CrudDao;

/**
 * General Dao class implementation. Made as an abstract class. C - input class,
 * N - Number for id
 */

public abstract class CrudlDaoImpl<E, N extends Number> implements
		CrudDao<E, N> {

	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public CrudlDaoImpl() {
		entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@PersistenceContext
	protected EntityManager em;

	/**
	 * Creates new entry of the entity in DB
	 */
	@Transactional
	public void save(E entity) {
		em.persist(entity);
	}

	/**
	 * Updates the existing entity
	 */
	@Transactional
	public void update(E entity) {
		em.merge(entity);
	}

	/**
	 * Returns the list of all entities
	 */
	@Transactional
	public List<E> getAll() {
		CriteriaQuery<E> cq = em.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
	}

	/**
	 * Deletes the entity
	 */
	@Transactional
	public void delete(E entity) {
		em.remove(entity);
	}

	/**
	 * Returns the entity by ID-number
	 */
	@Transactional
	public E findOneById(N id) {
		return em.find(entityClass, id);
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}
}