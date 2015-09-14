/**
 *
 */
package com.temple.service.ejb.model;

import com.temple.model.EntityKey;
import com.temple.model.TempleEntity;
import com.temple.model.UniqueEntityKey;
import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.FindMaxFilter;
import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.TempleManager;
import com.temple.service.model.EntityException;
import com.temple.service.model.FindEntityException;
import com.temple.service.model.UpdateException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 * TODOC
 * Is meant to be used locally as real interaction with clients shouldn't be made through this interface.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Local
public interface TempleEntityManager extends TempleManager {

	/**
	 * TODOC
	 *
	 * @param c
	 * @param id
	 * @return
	 * @throws FindEntityException
	 */
	// <E extends TempleEntity> E findReferenceById(Class<E> c, Serializable id) throws FindEntityException;
	/**
	 * Finds an {@link TempleEntity entity} given its id.
	 *
	 * @param <E>
	 * @param clazz an entity class
	 * @param id an id
	 * @return the entity with given class and id
	 * @throws FindEntityException if an error occurs while trying to find the entity or if none exists with the given
	 *             id.
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
	 * @param clazz an entity class
	 * @param id an id
	 * @return the entity with given class and id, <code>null</code> if none exists
	 * @throws FindEntityException if an error occurs while trying to find the entity
	 */
	<E extends TempleEntity> E findById(Class<E> clazz, Serializable id) throws FindEntityException;

	/**
	 * @param <E>
	 * @param ek
	 * @param values
	 * @return
	 * @throws FindEntityException
	 */
	<E extends TempleEntity> List<E> findByKey(EntityKey<E> ek, Serializable... values) throws FindEntityException;

	/**
	 * @param <E>
	 * @param ek
	 * @param values
	 * @return
	 * @throws FindEntityException
	 */
	<E extends TempleEntity> E findByKey(UniqueEntityKey<E> ek, Serializable... values) throws FindEntityException;

	/**
	 * TODOC
	 *
	 * @param <F>
	 * @param algo
	 * @return
	 * @throws FindEntityException
	 */
	<F extends Serializable> F findMax(FindMaxFilter<F> algo) throws FindEntityException;

	/**
	 * TODOC
	 *
	 * @param filter
	 * @return
	 * @throws FindEntityException
	 */
	long findCount(PageableEntityFilter<?> filter) throws FindEntityException;

	/**
	 * TODOC
	 *
	 * @param <E>
	 * @param ref
	 * @return
	 * @throws FindEntityException if an error occurs while trying to find the entities matching the filter
	 */
	<E extends TempleEntity> List<? extends E> findByFilter(EntityFilter<E> ref) throws FindEntityException;

	/**
	 * TODOC
	 *
	 * @param <E>
	 * @param filter
	 * @return
	 * @throws FindEntityException
	 */
//	<E extends TempleEntity> PageableResults<? extends E> getFirstPage(PageableEntityFilter<E> filter) throws FindEntityException;

	/**
	 * @param po
	 * @throws EntityException
	 */
	void persist(TempleEntity po) throws EntityException;

	/**
	 * TODOC
	 *
	 * @param tes
	 * @throws EntityException
	 */
	void persist(Collection<? extends TempleEntity> tes) throws EntityException;

	/**
	 * TODOC
	 *
	 * @param po
	 * @param flush
	 * @throws EntityException
	 */
	void persist(TempleEntity po, boolean flush) throws EntityException;

	/**
	 * @param tes
	 * @throws com.temple.service.model.UpdateException
	 */
	void merge(TempleEntity... tes) throws UpdateException;

	/**
	 * @param tes
	 * @throws com.temple.service.model.UpdateException
	 */
	void update(Collection<? extends TempleEntity> tes) throws UpdateException;

	/**
	 * TODOC
	 *
	 * @param tes
	 */
	void refresh(TempleEntity... tes);

	/**
	 * TODOC
	 *
	 * @param tes
	 */
	void refresh(Collection<? extends TempleEntity> tes);

	/**
	 * @param clazz
	 * @param ids
	 * @throws com.temple.service.model.EntityException
	 */
	void delete(Class<? extends TempleEntity> clazz, Serializable... ids) throws EntityException;

	/**
	 * @param po
	 * @throws com.temple.service.model.EntityException
	 */
	default void delete(TempleEntity po) throws EntityException {
		this.delete(po.getClass(), po.getId());
	}

	/**
	 * TODOC
	 *
	 * @param tes
	 * @throws EntityException
	 */
	default void delete(Collection<? extends TempleEntity> tes) throws EntityException {
		for (final TempleEntity te : tes) {
			this.delete(te.getClass(), te.getId());
		}
	}

	/**
	 * TODOC
	 *
	 * @param <E>
	 * @param ek
	 * @param values
	 * @return
	 * @throws EntityException
	 */
	<E extends TempleEntity> int deleteByKey(EntityKey<E> ek, Serializable... values) throws EntityException;
}
