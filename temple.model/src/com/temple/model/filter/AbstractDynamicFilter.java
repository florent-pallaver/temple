package com.temple.model.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.temple.model.TempleEntity;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 * @param <E>
 */
public abstract class AbstractDynamicFilter<E extends TempleEntity, R extends Serializable>
		implements EntityFilter<E, R> {

	private static final long serialVersionUID = 1L;

	@ToString
	private final List<FilterOrder<? super E>> orderBy;

	/**
	 * Constructor.
	 */
	protected AbstractDynamicFilter() {
		super();
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

	@Override
	public TypedQuery<R> createTypedQuery(EntityManager em) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final Class<E> ec = this.getEntityClass();
		final Class<R> rc = this.getResultClass();
		final CriteriaQuery<R> rootQuery = cb.createQuery(rc);
		final Root<E> root = rootQuery.from(ec);
		if (!rc.equals(ec)) {
			// if(selections.length > 0) {
			// rootQuery.select(cb.construct(resultClass, selections)) ;
			// }
		}
		rootQuery.where(this.createWherePredicate(cb, root, rootQuery));
		this.aggregate(cb, root);
		final int size = this.orderBy.size();
		if (size > 0) {
			final List<Order> collect = this.orderBy.stream().filter(o -> o.accepts(ec)).map(o -> {
				final OrderCriteria<? super E> criteria = o.getCriteria();
				final Path<?> field = root.get(criteria.field);
				return criteria.asc ? cb.asc(field) : cb.desc(field);
			}).collect(Collectors.toList());
			rootQuery.orderBy(collect);
		}
		final TypedQuery<R> q = em.createQuery(rootQuery);
		return q;
	}

	protected abstract Predicate createWherePredicate(CriteriaBuilder cb, final Root<? extends E> root,
			CriteriaQuery<?> rootQuery);

	/**
	 * For group by and having clauses ...
	 *
	 * @param cb
	 * @param root
	 */
	protected void aggregate(CriteriaBuilder cb, Root<? extends E> root) {
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}

}
