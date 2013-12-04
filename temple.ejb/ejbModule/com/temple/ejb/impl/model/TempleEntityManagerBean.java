package com.temple.ejb.impl.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

import com.temple.ejb.model.EntityException;
import com.temple.ejb.model.FindEntityException;
import com.temple.ejb.model.PageableResults;
import com.temple.ejb.model.TempleEntityManager;
import com.temple.ejb.model.UpdateException;
import com.temple.model.EntityKey;
import com.temple.model.TempleEntity;
import com.temple.model.filter.AbstractPageableEntityFilter;
import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.FindMaxFilter;

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
		try {
			return this.em.find(clazz, id);
		} catch (final PersistenceException e) {
			throw new FindEntityException(clazz, id, e);
		}
	}

	@Override
	public <E extends TempleEntity> List<E> findByKey(EntityKey<E> k) throws FindEntityException {
		final Class<E> entityClass = k.getEntityClass();
		final CriteriaQuery<E> q = this.cb.createQuery(entityClass);
		try {
			return this.em.createQuery(q.where(k.createPredicates(this.cb, q.from(entityClass)))).getResultList();
		} catch (final PersistenceException e) {
			throw new FindEntityException(entityClass, k, e);
		}
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
	public long findCount(AbstractPageableEntityFilter<?> filter) throws FindEntityException {
		try {
			return filter.createCountQuery(this.em).getSingleResult().longValue();
		} catch (final PersistenceException e) {
			throw new FindEntityException(filter, e);
		}
	}

	@Override
	public <E extends TempleEntity> List<E> findByFilter(EntityFilter<E> filter) throws FindEntityException {
		try {
			return filter.createTypedQuery(this.em).getResultList();
		} catch (final PersistenceException e) {
			throw new FindEntityException(filter, e);
		}
	}

	@Override
	public <E extends TempleEntity> PageableResults<E> getFirstPage(AbstractPageableEntityFilter<E> filter) throws FindEntityException {
		try {
			final int total = filter.createCountQuery(this.em).getSingleResult().intValue();
			final List<E> entities = total > 0 ? filter.createTypedQuery(this.em).getResultList() : Collections.<E> emptyList();
			return new PageableResults<>(entities, total);
		} catch (final PersistenceException e) {
			throw new FindEntityException(filter, e);
		}
	}

	@Override
	public void persist(TempleEntity po) throws EntityException {
		this.persist(po, false);
	}

	@Override
	public void persist(TempleEntity po, boolean flush) throws EntityException {
		if (this.isInfoLoggable()) {
			this.info("about to persist " + po);
		}
		try {
			this.em.persist(po);
			if (flush) {
				if (this.isFinestLoggable()) {
					this.finest("about to flush");
				}
				this.em.flush();
				if (this.isFinestLoggable()) {
					this.finest("flush done");
				}
			}
			if (this.isInfoLoggable()) {
				this.info("persisted " + po);
			}
		} catch (final PersistenceException e) {
			throw new EntityException(po, e);
		}
	}

	@Override
	public void persist(Collection<? extends TempleEntity> tes) throws EntityException {
		try {
			for (final TempleEntity te : tes) {
				this.em.persist(te);
			}
		} catch (final Exception e) {
			throw new EntityException(e);
		}
	}

	@Override
	public void update(TempleEntity po) throws UpdateException {
		try {
			this.em.merge(po);
		} catch (final PersistenceException e) {
			throw new UpdateException(po, e);
		}
	}

	@Override
	public void delete(TempleEntity po) throws EntityException {
		try {
			this.em.remove(po);
		} catch (final PersistenceException e) {
			throw new EntityException(po, e);
		}
	}

	@Override
	public void deleteById(Class<? extends TempleEntity> clazz, Serializable id) throws EntityException {
		try {
			this.em.remove(this.em.getReference(clazz, id));
		} catch (final PersistenceException e) {
			throw new EntityException(clazz, id, e);
		}
	}

	@Override
	public <E extends TempleEntity> int deleteByKey(EntityKey<E> k) throws EntityException {
		final Class<E> entityClass = k.getEntityClass();
		final CriteriaDelete<E> q = this.cb.createCriteriaDelete(entityClass);
		try {
			return this.em.createQuery(q.where(k.createPredicates(this.cb, q.from(entityClass)))).executeUpdate();
		} catch (final PersistenceException e) {
			throw new EntityException(entityClass, k, e);
		}
	}
}
