package com.temple.model.filter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.temple.model.TempleEntity;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class EntityFilterImpl<E extends TempleEntity> implements EntityFilter<E> {

	private static final long serialVersionUID = 1L;

	@ToString
	private final Class<E> entityClass;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param entityClass
	 */
	protected EntityFilterImpl(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	/**
	 * TODOC
	 * 
	 * @param em
	 * @return
	 */
	@Override
	public TypedQuery<E> createTypedQuery(EntityManager em) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<E> cq = cb.createQuery(this.entityClass);
		this.completeCriteria(cq, cb);
		final TypedQuery<E> q = em.createQuery(cq);
		return q;
	}

	protected abstract void completeCriteria(CriteriaQuery<E> cq, CriteriaBuilder cb);

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
