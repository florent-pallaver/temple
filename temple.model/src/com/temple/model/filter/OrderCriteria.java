package com.temple.model.filter;

import javax.persistence.metamodel.SingularAttribute;

import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class OrderCriteria<E extends TempleEntity> {

	// FIXME In EclipseLink SingularAttribute isn't Serializable
	public final SingularAttribute<? super E, ?> field;

	public final boolean asc;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param field
	 * @param asc
	 */
	private OrderCriteria(SingularAttribute<? super E, ?> field, boolean asc) {
		super();
		this.field = field;
		this.asc = asc;
	}

	/**
	 * TODOC
	 *
	 * @param field
	 * @return
	 */
	public static final <E extends TempleEntity> OrderCriteria<E> asc(SingularAttribute<? super E, ?> field) {
		return new OrderCriteria<E>(field, true);
	}

	/**
	 * TODOC
	 *
	 * @param field
	 * @return
	 */
	public static final <E extends TempleEntity> OrderCriteria<E> desc(SingularAttribute<? super E, ?> field) {
		return new OrderCriteria<E>(field, false);
	}
}
