package com.temple.model.filter;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 *            The entity class this filter applies to
 * @param <R>
 *            The result class
 */
public interface EntityFilter<E extends TempleEntity, R extends Serializable> extends Serializable {

	/**
	 * @return the filtered entity class
	 */
	Class<E> getEntityClass();

	/**
	 * @return the result class
	 */
	Class<R> getResultClass();

	/**
	 * TODOC
	 *
	 * @param em
	 * @return
	 */
	TypedQuery<R> createTypedQuery(EntityManager em);

}
