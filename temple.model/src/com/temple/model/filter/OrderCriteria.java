package com.temple.model.filter;

import com.temple.model.TempleEntity;
import javax.persistence.metamodel.SingularAttribute;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
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
	public OrderCriteria(SingularAttribute<? super E, ?> field, boolean asc) {
		super();
		this.field = field;
		this.asc = asc;
	}

}
