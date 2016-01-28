package com.temple.web.cdi.session;

import com.temple.model.TempleEntity;
import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.model.ModelManager;
import java.util.List;
import javax.inject.Inject;

/**
 * TODOC
 * @author flominou
 * @param <E>
 * @param <F>
 */
public class DefaultTempleEntityPagerBean<E extends TempleEntity, F extends PageableEntityFilter<E>> extends AbstractTempleEntityPagerBean<E, F>{

	private static final long serialVersionUID = 1L;
	
	@Inject
	@TempleObject
	private ModelManager mm ;

	protected DefaultTempleEntityPagerBean() {
		super() ;
	}
	
	public DefaultTempleEntityPagerBean(F filter) {
		super(filter);
	}
	
	@Override
	protected List<? extends E> getResults() throws ServiceException {
		return this.mm.find(this.filter) ;
	}

	@Override
	protected long getTotalCount() throws ServiceException {
		return this.mm.getCount(filter) ;
	}

	@Override
	protected void checkFilterChanged() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	
}
