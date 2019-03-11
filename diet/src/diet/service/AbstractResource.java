package diet.service;

import java.util.List;
import java.util.function.BiFunction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public abstract class AbstractResource<E> {

	@PersistenceContext
	protected EntityManager em;

	private final Class<E> clazz;

	AbstractResource() {
		this(null);
	}

	AbstractResource(Class<E> clazz) {
		super();
		this.clazz = clazz;
	}

	protected E get(int id) {
		return this.em.find(clazz, id);
	}
	
	protected List<E> getAll(BiFunction<CriteriaBuilder, Root<E>, List<Order>> orderFunction) {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<E> cq = cb.createQuery(clazz);
		final Root<E> root = cq.from(clazz);
		cq.orderBy(orderFunction.apply(cb, root));
		return this.em.createQuery(cq).getResultList();
	}

	protected void create(E entity) {
		this.em.persist(entity);
	}

	protected void delete(int id) {
		this.em.remove(this.em.getReference(clazz, id));
	}

}
