package com.temple.model.filter;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Selection;

import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E> The entity class this filter applies to
 */
public interface EntityFilter<E extends TempleEntity> extends Serializable {

	/**
	 * TODOC
	 *
	 * @param em
	 * @return
	 */
	TypedQuery<? extends E> createTypedQuery(EntityManager em);

	/**
	 * TODOC
	 * @param em
	 * @param result
	 * @param selections
	 * @return
	 */
	<R> TypedQuery<R> createTypedQuery(EntityManager em, Class<R> result, Selection<?> ... selections) ;

}
