package com.temple.service.ejb.model;

import com.temple.model.TempleEntity;
import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ejb.AbstractEJBBean;
import com.temple.service.model.ModelManager;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

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
	public <E extends TempleEntity> List<? extends E> find(EntityFilter<E> ef) throws ServiceException {
		return delegate.find(ef);
	}

	@Override
	public long getCount(PageableEntityFilter<?> pef) throws ServiceException {
		return delegate.getCount(pef);
	}

}
