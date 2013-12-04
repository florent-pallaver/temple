/**
 * 
 */
package com.temple.ejb.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.xml.registry.FindException;

import com.temple.ejb.TempleManager;
import com.temple.model.EntityKey;
import com.temple.model.TempleEntity;
import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.FindMaxFilter;
import com.temple.model.filter.AbstractPageableEntityFilter;

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
	 * @param clazz
	 * @param id
	 * @return
	 * @throws FindException
	 */
	<E extends TempleEntity> E findById(Class<E> clazz, Serializable id) throws FindEntityException;

	/**
	 * @param clazz
	 * @param nk
	 * @return
	 * @throws FindException
	 */
	<E extends TempleEntity> List<E> findByKey(EntityKey<E> nk) throws FindEntityException;

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
	long findCount(AbstractPageableEntityFilter<?> filter) throws FindEntityException;

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
	 * @param po
	 */
	void update(TempleEntity po) throws UpdateException;

	/**
	 * @param po
	 */
	void delete(TempleEntity po) throws EntityException;

	/**
	 * @param po
	 * @param id
	 */
	void deleteById(Class<? extends TempleEntity> clazz, Serializable id) throws EntityException;

	/**
	 * @param po
	 * @param nk
	 */
	<E extends TempleEntity> int deleteByKey(EntityKey<E> nk) throws EntityException;
}
