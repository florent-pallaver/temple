package com.temple.web.cdi;

import java.io.Serializable;
import java.util.List;

import com.temple.model.filter.EntityFilter;
import com.temple.util.Pageable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <R>
 * @param <F>
 */
public interface TempleEntityPager<R extends Serializable, F extends EntityFilter<?, R>> extends Pageable {

	/**
	 * @return the filter
	 */
	F getFilter();

	/**
	 * TODOC
	 *
	 * @return
	 */
	List<R> getAll();

	/**
	 * TODOC
	 *
	 * @param index
	 *            - an index
	 * @return <code>null</code> if no result, the object at the position
	 *         <code>index % results.size()</code>
	 */
	R getResult(int index);

	/**
	 * TODOC
	 *
	 * @return
	 */
	int getPageCount();
}
