package diet.service;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public abstract class AbstractBean<E> {

	@Inject
	protected EntityManagerBean emBean;

	private final Class<E> clazz;

	AbstractBean() {
		this(null);
	}

	AbstractBean(Class<E> clazz) {
		super();
		this.clazz = clazz;
	}

	protected E get(int id) {
		return this.emBean.get(clazz, id);
	}
	
	protected E getReference(int id) {
		return this.emBean.getReference(clazz, id);
	}
	
	protected List<E> getAll(BiFunction<CriteriaBuilder, Root<E>, List<Order>> orderFunction) {
		return this.emBean.getAll(clazz, orderFunction);
	}

	protected List<E> getAll_(BiConsumer<CriteriaBuilder, CriteriaQuery<E>> queryBuilder) {
		return emBean.getAll(clazz, queryBuilder);
	}

	protected void create(E entity) {
		this.emBean.create(entity);
	}

	protected void delete(int id) {
		this.emBean.delete(clazz, id);
	}
}
