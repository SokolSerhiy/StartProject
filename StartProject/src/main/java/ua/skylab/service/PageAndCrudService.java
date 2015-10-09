package ua.skylab.service;

import java.util.List;

public interface PageAndCrudService<E, N extends Number> extends
		CrudService<E, N> {

	List<E> getPage(String pathVariable);
	
	List<E> getPage();
}
