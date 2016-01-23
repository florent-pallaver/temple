package com.temple.service.model;

import com.temple.model.TempleEntity;
import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.ServiceException;
import com.temple.service.TempleManager;
import java.util.List;

/**
 * TODOC
 * @author flominou
 */
public interface ModelManager extends TempleManager {

	/**
	 * 
	 * @param <E>
	 * @param ef
	 * @return
	 * @throws ServiceException 
	 */
	<E extends TempleEntity> List<? extends E> find(EntityFilter<E> ef) throws ServiceException ;
	
	/**
	 * 
	 * @param pef
	 * @return
	 * @throws ServiceException 
	 */
	long getCount(PageableEntityFilter<?> pef) throws ServiceException ;
	
}
