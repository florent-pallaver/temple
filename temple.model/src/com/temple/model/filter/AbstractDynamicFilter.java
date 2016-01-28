package com.temple.model.filter;

import com.temple.model.TempleEntity;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;
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

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 */
public abstract class AbstractDynamicFilter<E extends TempleEntity> implements EntityFilter<E> {

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
	 * @return the entityClass to filter
	 */
	protected abstract Class<? extends E> getEntityClass();

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
	public TypedQuery<? extends E> createTypedQuery(EntityManager em) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final Class<? extends E> ec = this.getEntityClass();
		CriteriaQuery<? extends E> rootQuery = cb.createQuery(ec);
		final Root<? extends E> root = rootQuery.from(ec);
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
		final TypedQuery<? extends E> q = em.createQuery(rootQuery);
		return q;
	}

	protected abstract Predicate createWherePredicate(CriteriaBuilder cb, final Root<? extends E> root, CriteriaQuery<?> rootQuery);

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
