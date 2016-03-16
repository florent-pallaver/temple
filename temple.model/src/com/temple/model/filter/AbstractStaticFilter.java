package com.temple.model.filter;

import java.io.Serializable;

import com.temple.model.TempleEntity;
import com.temple.util.ToString;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 * @param <R>
 */
public abstract class AbstractStaticFilter<E extends TempleEntity, R extends Serializable>
		extends AbstractDynamicFilter<E, R> {

	private static final long serialVersionUID = 1L;

	@ToString
	private final Class<E> entityClass;

	@ToString
	private Class<R> resultClass;

	// Serializable
	protected AbstractStaticFilter() {
		this(null, null);
	}

	/**
	 * Constructor. TODOC
	 *
	 * @param entityClass
	 * @param resultClass
	 */
	protected AbstractStaticFilter(Class<E> entityClass, Class<R> resultClass) {
		super();
		this.entityClass = entityClass;
		this.resultClass = resultClass;
	}

	@Override
	public Class<E> getEntityClass() {
		return this.entityClass;
	}

	@Override
	public Class<R> getResultClass() {
		return this.resultClass;
	}

}
