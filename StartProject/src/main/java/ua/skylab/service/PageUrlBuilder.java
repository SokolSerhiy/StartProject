package ua.skylab.service;

import ua.skylab.service.implementation.PageAndCrudServiceImpl;

public interface PageUrlBuilder {
	
	void build(PageAndCrudServiceImpl<?, ?> pacs, int count,
			int pageNumber, int pageSize, String[] fieldNames, int[] posibleSizeOfPage);
	
	void build(PageAndCrudServiceImpl<?, ?> pacs, int count,
			int pageNumber, int pageSize, String[] fieldNames, String direction, String fieldName, int[] posibleSizeOfPage);
}
