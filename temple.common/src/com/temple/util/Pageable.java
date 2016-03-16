package com.temple.util;

/**
 * Base contract for an object able to provides page per page results. TODOC if
 * that makes any sense !
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Pageable {

	/**
	 * @return the count of item to display per page.
	 */
	short getPerPageCount();

	/**
	 * Sets the count of item to display per page.<br>
	 * A call to this method should reset the page to the first one.
	 *
	 * @param count
	 *            - the count
	 */
	void setPerPageCount(short count);

	/**
	 * @return the page (first page is 1)
	 */
	int getPage();

	/**
	 * Sets the current page. <br>
	 * It is implementation specific to decide what to do if the given page is
	 * out of the page count boundary.
	 *
	 * @param page
	 *            - the page to set
	 */
	void setPage(int page);

	/**
	 * Goes to the next page. <br>
	 * It is implementation specific to decide what to do if this method is
	 * called whilst on the last page
	 */
	void nextPage();

	/**
	 * Goes to the previous page. <br>
	 * It is implementation specific to decide what to do if this method is
	 * called whilst on the first page
	 */
	void previousPage();
}
