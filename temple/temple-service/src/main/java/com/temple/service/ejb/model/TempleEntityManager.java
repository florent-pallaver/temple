package com.temple.service.ejb.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import com.temple.model.EntityKey;
import com.temple.model.TempleEntity;
import com.temple.model.filter.Aggregator;
import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.LongAggregator;
import com.temple.service.TempleManager;
import com.temple.service.model.EntityException;
import com.temple.service.model.FindEntityException;
import com.temple.service.model.UpdateException;

/**
 * TODOC Is meant to be used locally as real interaction with clients shouldn't
 * be made through this interface.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Local
public interface TempleEntityManager extends TempleManager {

	/**
	 * Finds an {@link TempleEntity entity} given its id.
	 *
	 * @param <E>
	 * @param clazz
	 *            an entity class
	 * @param id
	 *            an id
	 * @return the entity with given class and id
	 * @throws FindEntityException
	 *             if an error occurs while trying to find the entity or if none
	 *             exists with the given id.
	 */
	default <E extends TempleEntity> E get(Class<E> clazz, Serializable id) throws FindEntityException {
		final E e = this.findById(clazz, id);
		if (e == null) {
			throw new FindEntityException(clazz, id);
		}
		return e;
	}

	/**
	 * Finds an {@link TempleEntity entity} given its id.
	 *
	 * @param <E>
	 * @param clazz
	 *            an entity class
	 * @param id
	 *            an id
	 * @return the entity with given class and id, <code>null</code> if none
	 *         exists
	 * @throws FindEntityException
	 *             if an error occurs while trying to find the entity
	 */
	<E extends TempleEntity> E findById(Class<E> clazz, Serializable id) throws FindEntityException;

	<E extends TempleEntity, ID extends Serializable> List<E> findByKey(EntityKey<E, ID> ek, ID[] values) throws FindEntityException;

	<E extends TempleEntity, R extends Serializable> R findAggregate(EntityFilter<E, ?> filter, Aggregator<E, R> agg) throws FindEntityException;

	default Long findCount(EntityFilter<?, ?> filter) throws FindEntityException {
		return this.findAggregate(filter, LongAggregator.count());
	}

	<E extends TempleEntity, R extends Serializable> List<R> findByFilter(EntityFilter<E, R> ref)
			throws FindEntityException;

	// <E extends TempleEntity> PageableResults<? extends E>
	// getFirstPage(PageableEntityFilter<E> filter) throws FindEntityException;

	default void persist(TempleEntity po) throws EntityException {
		this.persist(po, false);
	}

	void persist(Collection<? extends TempleEntity> tes) throws EntityException;

	void persist(TempleEntity po, boolean flush) throws EntityException;

	void merge(TempleEntity... tes) throws UpdateException;

	void update(Collection<? extends TempleEntity> tes) throws UpdateException;

	void refresh(TempleEntity... tes);

	void refresh(Collection<? extends TempleEntity> tes);

	void delete(Class<? extends TempleEntity> clazz, Serializable... ids) throws EntityException;

	default void delete(TempleEntity po) throws EntityException {
		this.delete(po.getClass(), po.getId());
	}

	default void delete(Collection<? extends TempleEntity> tes) throws EntityException {
		for (final TempleEntity te : tes) {
			this.delete(te.getClass(), te.getId());
		}
	}

	<E extends TempleEntity, ID extends Serializable> int deleteByKey(EntityKey<E, ID> ek, ID[] values) throws EntityException;
}
