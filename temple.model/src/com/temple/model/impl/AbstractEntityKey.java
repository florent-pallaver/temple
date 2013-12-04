package com.temple.model.impl;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.temple.model.TempleEntity;
import com.temple.model.EntityKey;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 */
public class AbstractEntityKey<E extends TempleEntity> implements EntityKey<E> {

	private static final long serialVersionUID = 1L;

	private final Class<E> entityClass;

	private final Serializable[] values;

	private final PredicateCreator[] predicateCreators;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param entityClass
	 * @param values
	 * @param predicateCreators
	 */
	protected AbstractEntityKey(Class<E> entityClass, Serializable[] values, PredicateCreator[] predicateCreators) {
		super();
		this.entityClass = entityClass;
		this.values = values;
		this.predicateCreators = predicateCreators;
	}

	@Override
	public Class<E> getEntityClass() {
		return this.entityClass;
	}

	@Override
	public Predicate[] createPredicates(CriteriaBuilder cb, Root<E> from) {
		final Predicate[] predicates = new Predicate[this.predicateCreators.length];
		for (int i = this.predicateCreators.length; i-- > 0;) {
			predicates[i] = this.predicateCreators[i].createPredicate(this.values[i]);
		}
		return predicates;
	}
}
