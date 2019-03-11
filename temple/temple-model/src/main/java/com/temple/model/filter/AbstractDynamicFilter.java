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
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;

import com.temple.model.TempleEntity;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;

public abstract class AbstractDynamicFilter<E extends TempleEntity, R extends Serializable>
		implements EntityFilter<E, R> {

	private static final long serialVersionUID = 1L;

	@ToString
	private final List<FilterOrder<? super E>> orderBy;

	protected AbstractDynamicFilter() {
		super();
		this.orderBy = new ArrayList<>();
	}

	public List<FilterOrder<? super E>> getOrderBy() {
		return this.orderBy;
	}

	public void setOrder(FilterOrder<? super E> order) {
		this.orderBy.clear();
		this.orderBy.add(order);
	}

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
		rootQuery.where(this.createWherePredicate(cb, root, rootQuery));
		if (!rc.equals(ec)) {
			final SingularAttribute<? super E, ?>[] selectedAttributes = this.getSelectedAttributes();
			if (selectedAttributes != null) {
				final Selection<?>[] selections = new Selection<?>[selectedAttributes.length];
				for (int i = 0; i < selectedAttributes.length; i++) {
					selections[i] = root.get(selectedAttributes[i]);
				}
				rootQuery.select(cb.construct(rc, selections));
			}
		}
		this.aggregate(cb, root, rootQuery);
		final int size = this.orderBy.size();
		if (size > 0) {
			final List<Order> collect = this.orderBy.stream().filter(o -> o.accepts(ec)).map(o -> {
				final Path<?> field = root.get(o.getField());
				return o.isAsc() ? cb.asc(field) : cb.desc(field);
			}).collect(Collectors.toList());
			rootQuery.orderBy(collect);
		}
		final TypedQuery<R> q = em.createQuery(rootQuery);
		return q;
	}

	protected abstract Predicate createWherePredicate(CriteriaBuilder cb, final Root<? extends E> root,
			CriteriaQuery<?> rootQuery);

	/**
	 * singularattributes aren't serializable ...
	 *
	 * @return
	 */
	protected SingularAttribute<? super E, ?>[] getSelectedAttributes() {
		return null;
	}

	/**
	 * For group by and having clauses ...
	 *
	 * @param cb
	 * @param root
	 * @param rootQuery
	 */
	protected void aggregate(CriteriaBuilder cb, Root<? extends E> root, CriteriaQuery<?> rootQuery) {
	}

	@Override
	public <AR extends Serializable> TypedQuery<AR> createAggregatedQuery(EntityManager em, Aggregator<E, AR> desc) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AR> cq = cb.createQuery(desc.getAggregateClass());
		final Root<? extends E> root = cq.from(this.getEntityClass());
		cq.select(desc.createAggregatedSelection(cb, root));
		cq.where(this.createWherePredicate(cb, root, cq));
		return em.createQuery(cq);
	}
	
	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
