package com.temple.geo.service;

import java.io.Serializable;
import java.util.List;

import com.temple.geo.model.CountryDivisionEntity;
import com.temple.geo.model.HumanSettlementEntity;
import com.temple.model.filter.EntityFilter;
import com.temple.service.ServiceException;
import com.temple.service.TempleManager;
import com.temple.util.geo.Country;

/**
 * TODOC
 *
 * @author flominou
 */
public interface GeoEntityManager extends TempleManager {

	/**
	 * TODOC
	 */
	String BEAN_NAME = "GeoEntityManagerBean";

	/**
	 *
	 * @param c
	 * @return
	 * @throws ServiceException
	 */
	List<? extends CountryDivisionEntity> findCountryDivisions(Country c) throws ServiceException;

	/**
	 *
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	List<? extends CountryDivisionEntity> findCountryDivisions(int parentId) throws ServiceException;

	/**
	 *
	 * @param c
	 * @return
	 * @throws ServiceException
	 */
	int countHumanSettlements(Country c) throws ServiceException;

	/**
	 *
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	int countHumanSettlements(int parentId) throws ServiceException;

	/**
	 *
	 * @param c
	 * @param from
	 * @param count
	 * @return
	 * @throws ServiceException
	 */
	<R extends Serializable> List<R> findHumanSettlements(EntityFilter<? extends HumanSettlementEntity, R> filter) throws ServiceException;

}
