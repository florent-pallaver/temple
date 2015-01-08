/**
 *
 */
package com.temple.ejb.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import com.temple.ejb.TempleManager;
import com.temple.model.EntityKey;
import com.temple.model.TempleEntity;
import com.temple.model.UniqueEntityKey;
import com.temple.model.filter.AbstractPageableEntityFilter;
import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.FindMaxFilter;
import com.temple.model.filter.PageableEntityFilter;

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
	 * @param clazz an entity class
	 * @param id an id
	 * @return the entity with given class and id, <code>null</code> if none exists
	 * @throws FindEntityException if an error occurs while trying to find the entity
	 */
	<E extends TempleEntity> E findById(Class<E> clazz, Serializable id) throws FindEntityException;

	/**
	 * @param ek
	 * @param values
	 * @return
	 * @throws FindEntityException
	 */
	<E extends TempleEntity> List<E> findByKey(EntityKey<E> ek, Serializable... values) throws FindEntityException;

	/**
	 * @param ek
	 * @param values
	 * @return
	 * @throws FindEntityException
	 */
	<E extends TempleEntity> E findByKey(UniqueEntityKey<E> ek, Serializable... values) throws FindEntityException;

	/**
	 * TODOC
	 *
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
	 * @param ref
	 * @return
	 * @throws FindEntityException - if an error occurs while trying to find the entities matching the filter
	 */
	<E extends TempleEntity> List<E> findByFilter(EntityFilter<E> ref) throws FindEntityException;

	/**
	 * TODOC
	 *
	 * @param filter
	 * @return
	 * @throws FindEntityException
	 */
	<E extends TempleEntity> PageableResults<E> getFirstPage(AbstractPageableEntityFilter<E> filter) throws FindEntityException;

	/**
	 * @param po
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
	 */
	void update(TempleEntity... tes) throws UpdateException;

	/**
	 * @param tes
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
	 * @param po
	 */
	void delete(TempleEntity po) throws EntityException;

	/**
	 * TODOC
	 *
	 * @param tes
	 * @throws EntityException
	 */
	void delete(Collection<? extends TempleEntity> tes) throws EntityException;

	/**
	 * @param clazz
	 * @param ids
	 */
	void deleteById(Class<? extends TempleEntity> clazz, Serializable... ids) throws EntityException;

	/**
	 * TODOC
	 *
	 * @param ek
	 * @param values
	 * @return
	 * @throws EntityException
	 */
	<E extends TempleEntity> int deleteByKey(EntityKey<E> ek, Serializable... values) throws EntityException;
}
