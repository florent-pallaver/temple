package com.temple.model;

import java.io.Serializable;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @param <E>
 */
public interface EntityKey<E extends TempleEntity> extends Serializable {

	/**
	 * TODOC
	 *
	 * @return
	 */
	Class<E> getEntityClass();

	/**
	 * TODOC
	 *
	 * @param cb
	 * @param from
	 * @param values
	 * @return
	 */
	Predicate[] createPredicates(CriteriaBuilder cb, Root<E> from, Serializable[] values);
}
