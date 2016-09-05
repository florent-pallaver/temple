package com.temple.model.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.temple.model.TempleEntity;

/**
 * Static filter that takes no criteria. Basically it selects all entities.
 *
 * @author flominou
 * @param <E>
 */
public class NoCriteriaFilter<E extends TempleEntity> extends AbstractStaticFilter<E, E> {

	private static final long serialVersionUID = 1L;

	public NoCriteriaFilter(Class<E> entityClass) {
		super(entityClass, entityClass);
	}

	@Override
	protected final Predicate createWherePredicate(CriteriaBuilder cb, Root<? extends E> root,
			CriteriaQuery<?> rootQuery) {
		return cb.and();
	}

}
