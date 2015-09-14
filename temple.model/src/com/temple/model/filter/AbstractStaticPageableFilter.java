package com.temple.model.filter;

import com.temple.model.TempleEntity;
import com.temple.util.ToString;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 */
public abstract class AbstractStaticPageableFilter<E extends TempleEntity> extends AbstractDynamicPageableFilter<E> {

	private static final long serialVersionUID = 1L;

	@ToString
	private final Class<E> entityClass ;

	// CDI
	AbstractStaticPageableFilter() {
		this(null) ;
	}
	
	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param entityClass
	 */
	protected AbstractStaticPageableFilter(Class<E> entityClass) {
		super() ;
		this.entityClass = entityClass ;
	}

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param entityClass
	 * @param perPageCount
	 */
	protected AbstractStaticPageableFilter(Class<E> entityClass, short perPageCount) {
		super(perPageCount);
		this.entityClass = entityClass ;
	}

	@Override
	protected Class<E> getEntityClass() {
		return this.entityClass ;
	}
	
}
