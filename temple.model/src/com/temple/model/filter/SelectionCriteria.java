package com.temple.model.filter;

import javax.persistence.metamodel.Attribute;

import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 */
public class SelectionCriteria<E extends TempleEntity> {

	// FIXME In EclipseLink SingularAttribute isn't Serializable
	public final Attribute<? super E, ?> field;

	/**
	 * Constructor. TODOC
	 *
	 * @param field
	 */
	public SelectionCriteria(Attribute<? super E, ?> field) {
		super();
		this.field = field;
	}

}
