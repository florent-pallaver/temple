package com.temple.model.filter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.temple.model.TempleEntity;
import com.temple.util.Pageable;
import com.temple.util.ToString;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractPageableEntityFilter<E extends TempleEntity> extends EntityFilterImpl<E> implements Pageable {

	private static final long serialVersionUID = 1L;

	/**
	 * TODOC
	 */
	public static final short DEFAULT_MAX_COUNT = 40;

	@ToString
	private int offset;

	@ToString
	private short maxCount;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param entityClass
	 * @param offset
	 * @param maxCount
	 */
	protected AbstractPageableEntityFilter(Class<E> entityClass) {
		this(entityClass, AbstractPageableEntityFilter.DEFAULT_MAX_COUNT);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param entityClass
	 * @param offset
	 * @param perPageCount
	 */
	protected AbstractPageableEntityFilter(Class<E> entityClass, short perPageCount) {
		super(entityClass);
		this.setPerPageCount(perPageCount);
	}

	@Override
	public final int getPage() {
		return this.offset / this.maxCount;
	}

	@Override
	public final void setPage(int page) {
		this.offset = page > 0 ? page * this.maxCount : 0;
	}

	@Override
	public final void nextPage() {
		this.offset += this.maxCount;
	}

	@Override
	public final void previousPage() {
		if (this.maxCount >= this.offset) {
			this.offset -= this.maxCount;
		}
	}

	@Override
	public short getPerPageCount() {
		return this.maxCount;
	}

	@Override
	public void setPerPageCount(short count) {
		this.maxCount = this.maxCount > 0 ? this.maxCount : AbstractPageableEntityFilter.DEFAULT_MAX_COUNT;
		this.offset = 0;
	}

	/**
	 * TODOC
	 * 
	 * @param em
	 * @return
	 */
	public final TypedQuery<Long> createCountQuery(EntityManager em) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		this.completeCount(cq, cb);
		final TypedQuery<Long> q = em.createQuery(cq);
		return q;
	}

	protected abstract void completeCount(CriteriaQuery<Long> cq, CriteriaBuilder cb);

	/**
	 * TODOC
	 * 
	 * @param em
	 * @return
	 */
	@Override
	public final TypedQuery<E> createTypedQuery(EntityManager em) {
		final TypedQuery<E> q = super.createTypedQuery(em);
		if (this.offset > 0) {
			q.setFirstResult(this.offset);
		}
		// maxCount always > 0
		q.setMaxResults(this.maxCount);
		return q;
	}
}
