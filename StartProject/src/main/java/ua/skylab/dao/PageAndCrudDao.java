package ua.skylab.dao;

import java.util.List;

public interface PageAndCrudDao<E, N extends Number> extends CrudDao<E, N>  {

	List<E> getPage(int firstResult, int maxResult);
	
	List<E> getPageASC(int firstResult, int maxResult, String fieldName);
	
	List<E> getPageDESC(int firstResult, int maxResult, String fieldName);
	
	long count();
	
	Class<E> getEntity();
}
