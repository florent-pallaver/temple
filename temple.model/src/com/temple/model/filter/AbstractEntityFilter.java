package com.temple.model.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.temple.model.TempleEntity;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractEntityFilter<E extends TempleEntity> implements EntityFilter<E> {

	private static final long serialVersionUID = 1L;

	@ToString
	protected final Class<E> entityClass;

	private final List<FilterOrder<? super E>> orderBy;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param entityClass
	 */
	protected AbstractEntityFilter(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
		this.orderBy = new ArrayList<>();
	}

	/**
	 * @return the orderBy
	 */
	public List<FilterOrder<? super E>> getOrderBy() {
		return this.orderBy;
	}

	/**
	 * TODOC
	 *
	 * @param order
	 */
	public void setOrder(FilterOrder<? super E> order) {
		this.orderBy.clear();
		this.orderBy.add(order);
	}

	/**
	 * TODOC
	 *
	 * @param orders
	 */
	public void setOrderBy(List<FilterOrder<? super E>> orders) {
		this.orderBy.clear();
		this.orderBy.addAll(orders);
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
		final Root<E> root = cq.from(this.entityClass);
		this.completeCriteriaQuery(cq, cb, root);
		final int size = this.orderBy.size();
		if (size > 0) {
			final Order[] orders = new Order[size];
			for (int i = size; i-- > 0;) {
				final OrderCriteria<? super E> criteria = this.orderBy.get(i).getCriteria();
				final Path<?> field = root.get(criteria.field);
				orders[i] = criteria.asc ? cb.asc(field) : cb.desc(field);
			}
			cq.orderBy(orders);
		}
		final TypedQuery<E> q = em.createQuery(cq);
		return q;
	}

	protected abstract void completeCriteriaQuery(CriteriaQuery<?> cq, CriteriaBuilder cb, final Root<? extends E> root);

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
