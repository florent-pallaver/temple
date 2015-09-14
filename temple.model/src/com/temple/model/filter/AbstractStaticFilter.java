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
public abstract class AbstractStaticFilter<E extends TempleEntity> extends AbstractDynamicFilter<E> {

	private static final long serialVersionUID = 1L;

	@ToString
	private final Class<E> entityClass;

	// Serializable
	protected AbstractStaticFilter() {
		this(null);
	}

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param entityClass
	 */
	protected AbstractStaticFilter(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	/**
	 * @return the entityClass
	 */
	@Override
	protected Class<E> getEntityClass() {
		return this.entityClass;
	}

}
