package com.temple.web.cdi.session;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.model.ModelManager;

/**
 * TODOC
 * 
 * @author flominou
 * @param <R>
 * @param <F>
 */
public class DefaultTempleEntityPagerBean<R extends Serializable, F extends PageableEntityFilter<?, R>>
		extends AbstractTempleEntityPagerBean<R, F> {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private ModelManager mm;

	protected DefaultTempleEntityPagerBean() {
		super();
	}

	public DefaultTempleEntityPagerBean(F filter) {
		super(filter);
	}

	@Override
	protected List<R> getResults() throws ServiceException {
		return this.mm.find(this.filter);
	}

	@Override
	protected long getTotalCount() throws ServiceException {
		return this.mm.getCount(this.filter);
	}

	@Override
	protected void checkFilterChanged() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}
