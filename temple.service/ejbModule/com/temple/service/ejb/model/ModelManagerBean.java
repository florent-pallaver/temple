package com.temple.service.ejb.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ejb.AbstractEJBBean;
import com.temple.service.model.ModelManager;

/**
 *
 * @author flominou
 */
@Stateless
public class ModelManagerBean extends AbstractEJBBean implements ModelManager {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private ModelManager delegate;

	@Override
	public <R extends Serializable> List<R> find(EntityFilter<?, R> ef) throws ServiceException {
		return this.delegate.find(ef);
	}

	@Override
	public long getCount(PageableEntityFilter<?, ?> pef) throws ServiceException {
		return this.delegate.getCount(pef);
	}

}
