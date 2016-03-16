package com.temple.service.ejb.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

import com.temple.model.EntityKey;
import com.temple.model.TempleEntity;
import com.temple.model.UniqueEntityKey;
import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.FindMaxFilter;
import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.model.EntityException;
import com.temple.service.model.FindEntityException;
import com.temple.service.model.UpdateException;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Stateless
@Local(TempleEntityManager.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TempleEntityManagerBean extends AbstractEntityManagerBean implements TempleEntityManager {

	private static final long serialVersionUID = 1L;

	@Override
	public <E extends TempleEntity> E findById(Class<E> clazz, Serializable id) throws FindEntityException {
		if (id == null) {
			throw new FindEntityException(clazz, id);
		}
		try {
			return this.em.find(clazz, id);
		} catch (final PersistenceException e) {
			throw new FindEntityException(clazz, id, e);
		}
	}

	@Override
	public <E extends TempleEntity> List<E> findByKey(EntityKey<E> ek, Serializable... values)
			throws FindEntityException {
		final Class<E> entityClass = ek.getEntityClass();
		final CriteriaQuery<E> q = this.cb.createQuery(entityClass);
		try {
			return this.em.createQuery(q.where(ek.createPredicates(this.cb, q.from(entityClass), values)))
					.getResultList();
		} catch (final PersistenceException e) {
			throw new FindEntityException(entityClass, ek, e);
		}
	}

	@Override
	public <E extends TempleEntity> E findByKey(UniqueEntityKey<E> uek, Serializable... values)
			throws FindEntityException {
		final EntityKey<E> ek = uek;
		final List<E> es = this.findByKey(ek, values);
		return es.isEmpty() ? null : es.get(0);
	}

	@Override
	public <F extends Serializable> F findMax(FindMaxFilter<F> algo) throws FindEntityException {
		try {
			return algo.createMaxQuery(this.em).getSingleResult();
		} catch (final NoResultException e) {
			return null;
		} catch (final PersistenceException e) {
			throw new FindEntityException(algo, e);
		}
	}

	@Override
	public long findCount(PageableEntityFilter<?, ?> filter) throws FindEntityException {
		if (this.isDebugLoggable()) {
			this.debug(filter);
		}
		try {
			return filter.createCountQuery(this.em).getSingleResult();
		} catch (final PersistenceException e) {
			throw new FindEntityException(filter, e);
		}
	}

	@Override
	public <E extends TempleEntity, R extends Serializable> List<R> findByFilter(EntityFilter<E, R> filter)
			throws FindEntityException {
		if (this.isDebugLoggable()) {
			this.debug(filter);
		}
		try {
			return filter.createTypedQuery(this.em).getResultList();
		} catch (final PersistenceException e) {
			throw new FindEntityException(filter, e);
		}
	}

	// @Override
	// public <E extends TempleEntity> PageableResults<? extends E>
	// getFirstPage(PageableEntityFilter<E> filter) throws FindEntityException {
	// final long total = this.findCount(filter) ;
	// try {
	// final List<? extends E> entities = total > 0 ?
	// filter.createTypedQuery(this.em).getResultList() :
	// Collections.emptyList();
	// return new PageableResults<>(entities, total);
	// } catch (final PersistenceException e) {
	// throw new FindEntityException(filter, e);
	// }
	// }

	@Override
	public void persist(TempleEntity po) throws EntityException {
		this.persist(po, false);
	}

	@Override
	public void persist(TempleEntity po, boolean flush) throws EntityException {
		if (this.isDebugLoggable()) {
			this.debug("about to persist " + po);
		}
		try {
			this.em.persist(po);
			if (flush) {
				if (this.isDebugLoggable()) {
					this.debug("about to flush");
				}
				this.em.flush();
				if (this.isDebugLoggable()) {
					this.debug("flush done");
				}
			}
			if (this.isDebugLoggable()) {
				this.debug("persisted " + po);
			}
		} catch (final PersistenceException e) {
			throw new EntityException(po, e);
		}
	}

	@Override
	public void persist(Collection<? extends TempleEntity> tes) throws EntityException {
		try {
			tes.stream().forEach(te -> this.em.persist(te));
		} catch (final Exception e) {
			throw new EntityException(e);
		}
	}

	@Override
	public void merge(TempleEntity... tes) throws UpdateException {
		this.update0(Arrays.asList(tes));
	}

	@Override
	public void update(Collection<? extends TempleEntity> tes) throws UpdateException {
		this.update0(tes);
	}

	private void update0(Iterable<? extends TempleEntity> tes) throws UpdateException {
		for (final TempleEntity te : tes) {
			try {
				this.em.merge(te);
			} catch (final PersistenceException e) {
				throw new UpdateException(te, e);
			}
		}
	}

	@Override
	public void refresh(TempleEntity... tes) {
		Arrays.stream(tes).forEach(te -> this.refresh0(te));
	}

	@Override
	public void refresh(Collection<? extends TempleEntity> tes) {
		tes.stream().forEach(te -> this.refresh0(te));
	}

	private void refresh0(TempleEntity te) {
		if (te != null) {
			// TOTHINK il semble que contains retourne toujours false ...
			final TempleEntity tr = this.em.contains(te) ? te : this.em.getReference(te.getClass(), te.getId());
			this.em.refresh(tr);
		}
	}

	@Override
	public void delete(Class<? extends TempleEntity> clazz, Serializable... ids) throws EntityException {
		for (final Serializable id : ids) {
			try {
				this.em.remove(this.em.getReference(clazz, id));
			} catch (final PersistenceException e) {
				throw new EntityException(clazz, id, e);
			}
		}
	}

	@Override
	public <E extends TempleEntity> int deleteByKey(EntityKey<E> ek, Serializable... values) throws EntityException {
		final Class<E> entityClass = ek.getEntityClass();
		final CriteriaDelete<E> q = this.cb.createCriteriaDelete(entityClass);
		try {
			return this.em.createQuery(q.where(ek.createPredicates(this.cb, q.from(entityClass), values)))
					.executeUpdate();
		} catch (final PersistenceException e) {
			throw new EntityException(entityClass, ek, e);
		}
	}
}
