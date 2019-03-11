package com.temple.service.model;

import java.io.Serializable;
import java.util.List;

import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.ServiceException;
import com.temple.service.TempleManager;

/**
 * TODOC
 *
 * @author flominou
 */
public interface ModelManager extends TempleManager {

	/**
	 * TODOC
	 *
	 * @param <E>
	 * @param <R>
	 * @param ef
	 * @return
	 * @throws ServiceException
	 */
	<R extends Serializable> List<R> find(EntityFilter<?, R> ef) throws ServiceException;

	/**
	 * TODOC
	 *
	 * @param pef
	 * @return
	 * @throws ServiceException
	 */
	long getCount(PageableEntityFilter<?, ?> pef) throws ServiceException;

}
