/**
 * 
 */
package com.temple.web.model;

import java.util.List;

import com.temple.model.TempleEntity;
import com.temple.util.Pageable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 * @param <M>
 */
public interface TempleEntityPagerBean<M extends TempleEntity> extends Pageable {

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
