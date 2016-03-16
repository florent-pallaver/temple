package com.temple.service.cdi.request;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.ServiceException;
import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ejb.model.TempleEntityManager;
import com.temple.service.model.ModelManager;

/**
 * TODOC
 *
 * @author flominou
 */
@RequestScoped
@TempleObject
public class ModelManagerBean extends AbstractCDIBean implements ModelManager {

	private static final long serialVersionUID = 1L;

	@EJB
	private TempleEntityManager tem;

	@Override
	public <R extends Serializable> List<R> find(EntityFilter<?, R> ef) throws ServiceException {
		return this.tem.findByFilter(ef);
	}

	@Override
	public long getCount(PageableEntityFilter<?, ?> pef) throws ServiceException {
		return this.tem.findCount(pef);
	}

}
