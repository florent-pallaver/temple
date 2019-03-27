package diet.service;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Dependent
@Transactional(TxType.REQUIRED)
public class EntityManagerBean {

	@PersistenceContext
	private EntityManager em;

	@Transactional(TxType.SUPPORTS)
	public <E> E get(Class<E> clazz, int id) {
		return this.em.find(clazz, id);
	}
	
	@Transactional(TxType.SUPPORTS)
	public <E> E getReference(Class<E> clazz, int id) {
		return this.em.getReference(clazz, id);
	}
	
	@Transactional(TxType.SUPPORTS)
	public <E> List<E> getAll(Class<E> clazz, BiFunction<CriteriaBuilder, Root<E>, List<Order>> orderFunction) {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<E> cq = cb.createQuery(clazz);
		final Root<E> root = cq.from(clazz);
		cq.orderBy(orderFunction.apply(cb, root));
		return this.em.createQuery(cq).getResultList();
	}
	
	@Transactional(TxType.SUPPORTS)
	public <E> List<E> getAll(Class<E> clazz, BiConsumer<CriteriaBuilder, CriteriaQuery<E>> queryBuilder) {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<E> cq = cb.createQuery(clazz);
		queryBuilder.accept(cb, cq);
		return this.em.createQuery(cq).getResultList();
	}

	public <E> E findOne(Class<E> clazz, BiConsumer<CriteriaBuilder, CriteriaQuery<E>> queryBuilder) {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<E> cq = cb.createQuery(clazz);
		queryBuilder.accept(cb, cq);
		return this.em.createQuery(cq).getSingleResult();
	}

	public void create(Object entity) {
		this.em.persist(entity);
		this.em.flush();
	}
	
	public <E> E merge(E entity) {
		final E mergedEntity = em.merge(entity);
		this.em.flush();
		return mergedEntity;
	}

	public <E> void delete(Class<E> clazz, int id) {
		this.em.remove(this.em.getReference(clazz, id));
	}
	
}
