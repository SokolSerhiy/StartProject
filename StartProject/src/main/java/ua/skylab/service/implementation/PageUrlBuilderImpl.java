package ua.skylab.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.skylab.service.PageUrlBuilder;

@Service
public class PageUrlBuilderImpl implements PageUrlBuilder {

	private static final int VISIBLE_PAGE_NUMBERS = 5;
	private static final String ASC = "asc";
	private static final String DESC = "desc";
	private static final String SLASH = "/";
	private static final String PAGE = "page";
	private static final String SPLITERATOR = "&";
	private static final int FIRST_PAGE_NUMBER = 1;

	public void build(PageAndCrudServiceImpl<?, ?> pacs, int count,
			int pageNumber, int pageSize, String[] fieldNames, int[] posibleSizeOfPage) {
		String generalUrl = buildUrl(pacs);
		String urlFirstPage = buildFirstPage(generalUrl, pageSize);
		pacs.setUrlFirstPage(urlFirstPage);
		String urlLastPage = buildLastPage(generalUrl, count, pageNumber, pageSize);
		pacs.setUrlLastPage(urlLastPage);
		String urlNextPage = buildNextPage(generalUrl, count, pageNumber, pageSize);
		pacs.setUrlNextPage(urlNextPage);
		String urlPreviousPage = buildPreviousPage(generalUrl, pageNumber, pageSize);
		pacs.setUrlPreviousPage(urlPreviousPage);
		List<String> listUrlsOfPageNumbers = buildListUrlsOfPageNumbers(generalUrl, count, pageNumber, pageSize);
		pacs.setListUrlsOfPageNumbers(listUrlsOfPageNumbers);
		List<String> urlsChangeFieldForSorting = buildChangeFieldForSorting(generalUrl, pageNumber, pageSize, fieldNames);
		pacs.setUrlsChangeFieldForSorting(urlsChangeFieldForSorting);
		List<String> urlsChangeSizeOfPage = buildChangeSizeOfPage(generalUrl, posibleSizeOfPage);
		pacs.setUrlsChangeSizeOfPage(urlsChangeSizeOfPage);
	}
	
	public void build(PageAndCrudServiceImpl<?, ?> pacs, int count,
			int pageNumber, int pageSize, String[] fieldNames, String direction, String fieldName, int[] posibleSizeOfPage) {
		String generalUrl = buildUrl(pacs);
		String urlFirstPage = buildFirstPage(generalUrl, pageNumber) + addDirection(fieldName, direction);
		pacs.setUrlFirstPage(urlFirstPage);
		String urlLastPage = buildLastPage(generalUrl, count, pageNumber, pageSize) + addDirection(fieldName, direction);
		pacs.setUrlLastPage(urlLastPage);
		String urlNextPage = buildNextPage(generalUrl, count, pageNumber, pageSize) + addDirection(fieldName, direction);
		pacs.setUrlNextPage(urlNextPage);
		String urlPreviousPage = buildPreviousPage(generalUrl, pageNumber, pageSize) + addDirection(fieldName, direction);
		pacs.setUrlPreviousPage(urlPreviousPage);
		List<String> listUrlsOfPageNumbers = buildListUrlsOfPageNumbers(generalUrl, count, pageNumber, pageSize);
		listUrlsOfPageNumbers = addDirection(listUrlsOfPageNumbers, fieldName, direction);
		pacs.setListUrlsOfPageNumbers(listUrlsOfPageNumbers);
		List<String> urlsChangeFieldForSorting = buildChangeFieldForSorting(generalUrl, pageNumber, pageSize, fieldNames);
		urlsChangeFieldForSorting = addDirection(urlsChangeFieldForSorting, fieldName, direction);
		pacs.setUrlsChangeFieldForSorting(urlsChangeFieldForSorting);
		List<String> urlsChangeSizeOfPage = buildChangeSizeOfPage(generalUrl, posibleSizeOfPage);
		urlsChangeSizeOfPage = addDirection(urlsChangeSizeOfPage, fieldName, direction);
		pacs.setUrlsChangeSizeOfPage(urlsChangeSizeOfPage);
		List<String> urlChooseSortingDirection = buildChooseSortingDirection(generalUrl, pageNumber, pageSize, fieldName);
		pacs.setUrlChooseSortingDirection(urlChooseSortingDirection);
	}
	
	public List<String> buildChooseSortingDirection(String generalUrl, int pageNumber, int pageSize, String fieldName){
		List<String> urlChooseSortingDirection = new ArrayList<String>();
		urlChooseSortingDirection.add(generalUrl + pageNumber + SPLITERATOR + pageSize + SPLITERATOR + ASC + SPLITERATOR + fieldName);
		urlChooseSortingDirection.add(generalUrl + pageNumber + SPLITERATOR + pageSize + SPLITERATOR + DESC + SPLITERATOR +  fieldName);
		return urlChooseSortingDirection;
	}
	
	public List<String> addDirection(List<String> list, String fieldName, String derection){
		for(int i = 0; i < list.size(); i++){
			String tmp = list.get(i) + addDirection(fieldName, derection);
			list.remove(i);
			list.add(i, tmp);
		}
		return list;
	}
	
	public String addDirection(String fieldName, String derection){
		return SPLITERATOR + derection + SPLITERATOR + fieldName;
	}
	
	public String buildFirstPage(String generalUrl, int pageSize) {
		return generalUrl + 1 + SPLITERATOR + pageSize;
	}
	
	public String buildLastPage(String generalUrl, int count, int pageNumber, int pageSize) {
		return generalUrl
				+ ((int) Math.ceil((double) count / (double) pageNumber))
				+ SPLITERATOR + pageSize;
	}
	
	public String buildCurentUrl(String generalUrl, int pageNumber, int pageSize) {
		return generalUrl + pageNumber + SPLITERATOR + pageSize;
	}
	
	public String buildNextPage(String generalUrl, int count, int pageNumber,
			int pageSize) {
		String urlNextPage;
		int maxCountOfPage = (int) Math.ceil((double) count
				/ (double) pageSize);
		if (pageNumber < maxCountOfPage) {
			urlNextPage = generalUrl + (pageNumber + 1) + SPLITERATOR
					+ pageSize;
		} else {
			urlNextPage = generalUrl + pageNumber + SPLITERATOR
					+ pageSize;
		}
		return urlNextPage;
	}

	public String buildPreviousPage(String generalUrl, int pageNumber, int pageSize) {
		String urlPreviousPage;
		if (pageNumber > 1) {
			urlPreviousPage = generalUrl + (pageNumber - 1) + SPLITERATOR
					+ pageSize;
		} else {
			urlPreviousPage = generalUrl + pageNumber + SPLITERATOR
					+ pageSize;
		}
		return urlPreviousPage;
	}

	public List<String> buildListUrlsOfPageNumbers(String generalUrl,
			int count, int pageNumber, int pageSize) {
		List<String> listUrlsOfPageNumbers = new ArrayList<String>();
		int startPosition = pageNumber - (VISIBLE_PAGE_NUMBERS / 2);
		int lastPosition = (int) Math.ceil((double) count
				/ (double) pageSize);
		int diferent = lastPosition - startPosition;
		if (diferent < VISIBLE_PAGE_NUMBERS) {
			startPosition = lastPosition++ - VISIBLE_PAGE_NUMBERS;
		}
		if (startPosition <= 1) {
			startPosition = 1;
		}
		if (diferent >= VISIBLE_PAGE_NUMBERS) {
			for (int i = 0; i < VISIBLE_PAGE_NUMBERS; i++) {
				listUrlsOfPageNumbers.add(generalUrl + startPosition
						+ SPLITERATOR + pageSize);
				startPosition++;
			}
		} else {
			while (startPosition <= lastPosition) {
				listUrlsOfPageNumbers.add(generalUrl + startPosition
						+ SPLITERATOR + pageSize);
				startPosition++;
			}
		}
		return listUrlsOfPageNumbers;
	}

	public List<String> buildChangeFieldForSorting(String generalUrl,
			int pageNumber, int pageSize, String[] fieldNames) {
		List<String> urlsChangeFieldForSorting = new ArrayList<String>();
		for (String fieldName : fieldNames) {
			urlsChangeFieldForSorting
					.add(buildCurentUrl(generalUrl, pageNumber, pageSize) + SPLITERATOR
							+ ASC + SPLITERATOR + fieldName);
		}
		return urlsChangeFieldForSorting;
	}

	public List<String> buildChangeSizeOfPage(String generalUrl, int[] posibleSizeOfPage) {
		List<String> urlsChangeSizeOfPage = new ArrayList<String>();
		
		for (int pageSize : posibleSizeOfPage) {
			urlsChangeSizeOfPage.add(generalUrl + FIRST_PAGE_NUMBER
					+ SPLITERATOR + pageSize);
		}
		return urlsChangeSizeOfPage;
	}

	public String buildUrl(PageAndCrudServiceImpl<?, ?> pacs) {
		return pacs.getEntity().getSimpleName().toLowerCase() + SLASH + PAGE
				+ SLASH;
	}
}
