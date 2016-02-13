package com.temple.model.filter;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.temple.model.TempleEntity;
import com.temple.util.ToString;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 */
public abstract class AbstractDynamicPageableFilter<E extends TempleEntity> extends AbstractDynamicFilter<E> implements PageableEntityFilter<E> {

	private static final long serialVersionUID = 1L;

	/**
	 * TODOC
	 */
	public static final short DEFAULT_MAX_COUNT = 30;

	@ToString
	private int offset;

	@ToString
	private short maxCount;

	@ToString
	private boolean maxCountIgnored = false ;

	/**
	 * Constructor.
	 */
	protected AbstractDynamicPageableFilter() {
		this(AbstractDynamicPageableFilter.DEFAULT_MAX_COUNT);
	}

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param perPageCount
	 */
	protected AbstractDynamicPageableFilter(short perPageCount) {
		super();
		this.setPerPageCount(perPageCount);
	}

	@Override
	public int getPage() {
		return 1 + this.offset / this.maxCount;
	}

	@Override
	public void setPage(int page) {
		this.offset = page > 1 ? (page - 1) * this.maxCount : 0;
	}

	@Override
	public void nextPage() {
		this.offset += this.maxCount;
	}

	@Override
	public void previousPage() {
		if (this.maxCount <= this.offset) {
			this.offset -= this.maxCount;
		} else {
			this.offset = 0 ;
		}
	}

	@Override
	public short getPerPageCount() {
		return this.maxCount;
	}

	@Override
	public void setPerPageCount(short count) {
		this.maxCount = count > 0 ? count : AbstractDynamicPageableFilter.DEFAULT_MAX_COUNT;
		this.offset = 0;
	}

	/**
	 * TODOC
	 * @param i
	 */
	protected void setMaxCountIgnored(boolean i) {
		this.maxCountIgnored = i ;
	}

	/**
	 * TODOC
	 *
	 * @param em
	 * @return
	 */
	@Override
	public TypedQuery<Long> createCountQuery(EntityManager em) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<? extends E> root = cq.from(this.getEntityClass());
		cq.select(cb.count(root.get(this.getCountedField())));
		cq.where(this.createWherePredicate(cb, root, cq)) ;
		final TypedQuery<Long> q = em.createQuery(cq);
		return q;
	}

	/**
	 * TODOC {@link SingularAttribute} is not {@link Serializable}
	 *
	 * @return
	 */
	protected abstract SingularAttribute<? super E, ?> getCountedField();

	/**
	 * TODOC
	 *
	 * @param em
	 * @return
	 */
	@Override
	public TypedQuery<? extends E> createTypedQuery(EntityManager em) {
		final TypedQuery<? extends E> q = super.createTypedQuery(em);
		if (this.offset > 0) {
			q.setFirstResult(this.offset);
		}
		if(!this.maxCountIgnored) {
			// maxCount always > 0
			q.setMaxResults(this.maxCount);
		}
		return q;
	}
}
