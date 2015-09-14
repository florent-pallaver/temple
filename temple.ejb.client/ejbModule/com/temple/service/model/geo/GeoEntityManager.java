package com.temple.service.model.geo;

import com.temple.model.geo.CountryDivisionEntity;
import com.temple.model.geo.HumanSettlementEntity;
import com.temple.service.ServiceException;
import com.temple.service.TempleManager;
import com.temple.util.geo.Country;
import java.util.List;

/**
 * TODOC
 * @author flominou
 */
public interface GeoEntityManager extends TempleManager {
	
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
