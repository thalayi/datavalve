package org.jdataset;

import java.util.List;

/**
 * This is the core interface for the object datsets. It provides methods for
 * accessing the data, record counts, pagination and navigation of
 * the dataset.
 * 
 * @author Andy Gibson
 * 
 * @param <T>
 *            Type of value this dataset returns
 */
public interface ObjectDataset<T> {

	/**
	 * @return The list of results for this query
	 */
	List<T> getResults();

	/**
	 * @return The total number of results for this query
	 */
	Integer getResultCount();

	/**
	 * @return The index of the first result to return
	 */
	int getFirstResult();

	/**
	 * @param firstResult
	 *            the index to use for the first result
	 */
	void setFirstResult(Integer firstResult);

	/**
	 * @return the maximum number of rows to return
	 */
	int getMaxRows();

	/**
	 * @param maxRows
	 *            the maximum number of rows to return
	 */
	void setMaxRows(Integer maxRows);

	/**
	 * Causes the list of results to be invalidated and re-fetched on the next
	 * request. This is usually called when the first result or result count
	 * changes
	 */
	void invalidateResults();

	/**
	 * Called when both the result list and the result count are invalid.
	 */
	void invalidateResultInfo();

	/**
	 * @return The current page based on the first result and the max rows
	 *         returned
	 */
	int getPage();

	/**
	 * @return The number of pages based on the resultCount and the max rows
	 *         returned
	 */
	int getPageCount();

	/**
	 * Sets the first result index to 0, fetching the first page of results, or
	 * refreshing all results if paging is not used.
	 */
	void first();

	/**
	 * Goes to the last page of results, or refreshes the results from the first
	 * page if paging is not used.
	 */
	void last();

	/**
	 * @return Indicates whether there is a page after the current one to
	 *         navigate to
	 */
	boolean isNextAvailable();

	/**
	 * @return Indicates whether there is a page before the current one to
	 *         navigate to
	 */
	boolean isPreviousAvailable();

	/**
	 * Moves to the next page in the result set. Does nothing if there is no
	 * next page to go to (including if this is a non-paged dataset)
	 */
	void next();

	/**
	 * Moves to the previous page in the result set. Does nothing if there is no
	 * previous page to go to (including if this is a non-paged dataset)
	 */
	void previous();

	/**
	 * @return Indicates whether this dataset is paged, or whether it will
	 *         return all results based on whether the max rows property is set.
	 */
	boolean isPaged();

	/**
	 * @return Indicates whether this dataset has more than one page. Can be
	 *         used to indicate whether paging is needed. This value is based on
	 *         the actual results as opposed to the dataset settings.
	 */
	boolean isMultiPaged();

}
