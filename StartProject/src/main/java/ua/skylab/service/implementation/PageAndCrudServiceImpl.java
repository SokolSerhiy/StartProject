package ua.skylab.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ua.skylab.dao.PageAndCrudDao;
import ua.skylab.service.CrudService;
import ua.skylab.service.PageUrlBuilder;

public abstract class PageAndCrudServiceImpl<E, N extends Number> extends
		CrudServiceImpl<E, N> implements CrudService<E, N>{

	private final int DEFAULT_PAGE_NUMBER = 1;
	private final int ONE_ELEMENT = 1;
	private final int TWO_ELEMENT = 2;
	private final int FOUR_ELEMENT = 4;
	private final int START_ROW = 0;
	private final int DEFAULT_PAGE_SIZE = 5;
	private final String SPLITERATOR = "&";
	private final String ASC = "asc";
	private final int[] posibleSizeOfPage;
	private String[] fieldNames;
	private String urlFirstPage;
	private String urlLastPage;
	private String urlNextPage;
	private String urlPreviousPage;
	private List<String> listUrlsOfPageNumbers = new ArrayList<String>();
	private List<String> urlsChangeFieldForSorting = new ArrayList<String>();
	private List<String> urlsChangeSizeOfPage = new ArrayList<String>();
	private List<String> urlChooseSortingDirection = new ArrayList<String>();

	@Autowired
	private PageUrlBuilder pageUrlBuilder;
	
	@Autowired
	private PageAndCrudDao<E, N> pageAndCrudDao;
	
	public PageAndCrudServiceImpl(int[] posibleSizeOfPage, String... fieldNames){
		this.fieldNames = fieldNames;
		this.posibleSizeOfPage = posibleSizeOfPage;
	}

	public List<E> getPage(String pathVariable) {
		StringTokenizer strt = new StringTokenizer(pathVariable, SPLITERATOR);
		if (strt.countTokens() == ONE_ELEMENT) {
			int pageNumber = getPageNumber(strt);
			pageUrlBuilder.build(this, (int)pageAndCrudDao.count(), pageNumber, DEFAULT_PAGE_SIZE, fieldNames, posibleSizeOfPage);
			return getPage(pageNumber);
		} else if (strt.countTokens() == TWO_ELEMENT) {
			int pageNumber = getPageNumber(strt);
			int pageSize = getPageSize(strt);
			pageUrlBuilder.build(this, (int)pageAndCrudDao.count(), pageNumber, pageSize, fieldNames, posibleSizeOfPage);
			return getPage(pageNumber, pageSize);
		} else if (strt.countTokens() == FOUR_ELEMENT) {
			int pageNumber = getPageNumber(strt);
			int pageSize = getPageSize(strt);
			String direction = getSortDirection(strt);
			String fieldName = getFieldName(strt);
			pageUrlBuilder.build(this, (int)pageAndCrudDao.count(), pageNumber, pageSize, fieldNames, direction, fieldName, posibleSizeOfPage);
			return getPage(pageNumber, pageSize, fieldName, direction);
		} else {
			throw new IllegalArgumentException("Wrong path variable");
		}
	}

	public int getPageNumber(StringTokenizer strt) {
		try {
			return Integer.parseInt(strt.nextElement().toString());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Wrong path variable");
		}
	}

	public int getPageSize(StringTokenizer strt) {
		try {
			return Integer.parseInt(strt.nextElement().toString());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Wrong path variable");
		}
	}

	public String getSortDirection(StringTokenizer strt) {
		try {
			return strt.nextElement().toString();
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Wrong path variable");
		}
	}

	public String getFieldName(StringTokenizer strt) {
		try {
			return strt.nextElement().toString();
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Wrong path variable");
		}
	}

	@Transactional
	public List<E> getPage(int pageNumber, int pageSize, String fieldName,
			String direction) {
		int count = (int) pageAndCrudDao.count();
		int firstResult = correctFirstResult(count, pageNumber, pageSize);
		int maxResult = correctMaxResult(count, pageNumber, pageSize);
		if (direction.equalsIgnoreCase(ASC)) {
			return pageAndCrudDao.getPageASC(firstResult, maxResult, fieldName);
		} else {
			return pageAndCrudDao
					.getPageDESC(firstResult, maxResult, fieldName);
		}
	}

	@Transactional
	public List<E> getPage(int pageNumber, int pageSize) {
		int count = (int) pageAndCrudDao.count();
		int firstResult = correctFirstResult(count, pageNumber, pageSize);
		int maxResult = correctMaxResult(count, pageNumber, pageSize);
		return pageAndCrudDao.getPage(firstResult, maxResult);
	}

	@Transactional
	public List<E> getPage(int pageNumber) {
		int count = (int) pageAndCrudDao.count();
		int firstResult = correctFirstResult(count, pageNumber,
				DEFAULT_PAGE_SIZE);
		int maxResult = correctMaxResult(count, pageNumber, DEFAULT_PAGE_SIZE);
		return pageAndCrudDao.getPage(firstResult, maxResult);
	}

	@Transactional
	public List<E> getPage() {
		int count = (int) pageAndCrudDao.count();
		int maxResult = correctMaxResult(count, DEFAULT_PAGE_NUMBER,
				DEFAULT_PAGE_SIZE);
		return pageAndCrudDao.getPage(START_ROW, maxResult);
	}

	public int correctFirstResult(int count, int pageNumber, int pageSize) {
		if (!isLastPage(count, pageNumber, pageSize)) {
			return ((pageNumber - 1) * pageSize);
		} else {
			int maxCountOfPage = count / pageSize;
			return (maxCountOfPage * pageSize);
		}
	}

	public int correctMaxResult(int count, int pageNumber, int pageSize) {
		int maxCountOfPage = count / pageSize;
		if (!isLastPage(count, pageNumber, pageSize)) {
			return pageSize;
		} else {
			return count - (maxCountOfPage * pageSize);
		}
	}

	public boolean isLastPage(int count, int pageNumber, int pageSize) {
		int maxCountOfPage = count / pageSize;
		if (maxCountOfPage >= pageNumber) {
			return false;
		} else {
			return true;
		}
	}

	public String getUrlFirstPage() {
		return urlFirstPage;
	}

	public void setUrlFirstPage(String urlFirstPage) {
		this.urlFirstPage = urlFirstPage;
	}

	public String getUrlLastPage() {
		return urlLastPage;
	}

	public void setUrlLastPage(String urlLastPage) {
		this.urlLastPage = urlLastPage;
	}

	public String getUrlNextPage() {
		return urlNextPage;
	}

	public void setUrlNextPage(String urlNextPage) {
		this.urlNextPage = urlNextPage;
	}

	public String getUrlPreviousPage() {
		return urlPreviousPage;
	}

	public void setUrlPreviousPage(String urlPreviousPage) {
		this.urlPreviousPage = urlPreviousPage;
	}

	public List<String> getListUrlsOfPageNumbers() {
		return listUrlsOfPageNumbers;
	}

	public void setListUrlsOfPageNumbers(List<String> listUrlsOfPageNumbers) {
		this.listUrlsOfPageNumbers = listUrlsOfPageNumbers;
	}

	public List<String> getUrlsChangeFieldForSorting() {
		return urlsChangeFieldForSorting;
	}

	public void setUrlsChangeFieldForSorting(List<String> urlsChangeFieldForSorting) {
		this.urlsChangeFieldForSorting = urlsChangeFieldForSorting;
	}

	public List<String> getUrlsChangeSizeOfPage() {
		return urlsChangeSizeOfPage;
	}

	public void setUrlsChangeSizeOfPage(List<String> urlsChangeSizeOfPage) {
		this.urlsChangeSizeOfPage = urlsChangeSizeOfPage;
	}

	public List<String> getUrlChooseSortingDirection() {
		List<String> list = new ArrayList<String>(urlChooseSortingDirection);
		urlChooseSortingDirection.clear();
		return list;
	}

	public void setUrlChooseSortingDirection(List<String> urlChooseSortingDirection) {
		this.urlChooseSortingDirection = urlChooseSortingDirection;
	}
	
	public Class<E> getEntity(){
		return pageAndCrudDao.getEntity();
	}
}
