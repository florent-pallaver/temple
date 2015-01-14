package com.temple.web.cdi;

import java.util.List;

import com.temple.model.TempleEntity;
import com.temple.model.filter.EntityFilter;
import com.temple.util.Pageable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 * @param <M>
 * @param <F>
 */
public interface TempleEntityPager<M extends TempleEntity, F extends EntityFilter<M>> extends Pageable {

	/**
	 * @return the filter
	 */
	F getFilter();

	/**
	 * TODOC
	 *
	 * @return
	 */
	List<M> getAll();

	/**
	 * TODOC
	 *
	 * @param index - an index
	 * @return <code>null</code> if no result, the object at the position <code>index % results.size()</code>
	 */
	M getResult(int index);

	/**
	 * TODOC
	 *
	 * @return
	 */
	int getPageCount();
}
