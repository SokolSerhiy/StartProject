package ua.skylab.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import ua.skylab.dao.PageAndCrudDao;

public abstract class PageAndCrudDaoImpl<E, N extends Number> extends
		CrudlDaoImpl<E, N> implements PageAndCrudDao<E, N> {

	public List<E> getPage(String pathVariable) {
		
		return null;
	}
	
	@Transactional
	public List<E> getPage(int firstResult, int maxResult) {
		CriteriaQuery<E> cq = em.getCriteriaBuilder().createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).setFirstResult(firstResult)
				.setMaxResults(maxResult).getResultList();
	}

	@Transactional
	public List<E> getPageASC(int firstResult, int maxResult, String fieldName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(entityClass);
		Root<E> root = cq.from(entityClass);
		cq.select(root);
		cq.orderBy(cb.asc(root.get(fieldName)));
		return em.createQuery(cq).setFirstResult(firstResult)
				.setMaxResults(maxResult).getResultList();
	}

	@Transactional
	public List<E> getPageDESC(int firstResult, int maxResult, String fieldName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(entityClass);
		Root<E> root = cq.from(entityClass);
		cq.select(root);
		cq.orderBy(cb.desc(root.get(fieldName)));
		return em.createQuery(cq).setFirstResult(firstResult)
				.setMaxResults(maxResult).getResultList();
	}
	
	@Transactional
	public long count() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(entityClass)));
		return em.createQuery(cq).getSingleResult();
	}
	
	public Class<E> getEntity(){
		return entityClass;
	}
}
