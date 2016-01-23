package com.temple.geo.service;

import java.util.List;

import com.temple.geo.model.CountryDivisionEntity;
import com.temple.geo.model.HumanSettlementEntity;
import com.temple.service.ServiceException;
import com.temple.service.TempleManager;
import com.temple.util.geo.Country;

/**
 * TODOC
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
	List<? extends CountryDivisionEntity> findCountryDivisions(Country c) throws ServiceException ;

	/**
	 *
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	List<? extends CountryDivisionEntity> findCountryDivisions(int parentId) throws ServiceException ;

	/**
	 *
	 * @param c
	 * @return
	 * @throws ServiceException
	 */
	List<? extends HumanSettlementEntity> findHumanSettlements(Country c) throws ServiceException ;

	/**
	 *
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	List<? extends HumanSettlementEntity> findHumanSettlements(int parentId) throws ServiceException ;

}
