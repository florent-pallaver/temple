/**
 * 
 */
package com.temple.web.model;

import java.util.List;

import com.temple.LocaleViewableTempleException;
import com.temple.ejb.model.PartialResults;
import com.temple.model.TempleEntity;
import com.temple.model.filter.AbstractPageableEntityFilter;
import com.temple.web.cdi.request.AbstractRequestBean;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 * @param <M>
 */
public abstract class AbstractTempleEntityPagerBean<M extends TempleEntity> extends AbstractRequestBean implements TempleEntityPagerBean<M> {

	// @Inject
	// private Conversation conversation;
	protected AbstractPageableEntityFilter<M> filter;

	protected int totalCount = 0;

	protected int pageCount = 0;

	protected List<M> results = null;

	AbstractTempleEntityPagerBean() {
		this(null);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param filter
	 */
	protected AbstractTempleEntityPagerBean(AbstractPageableEntityFilter<M> filter) {
		super();
		this.filter = filter;
	}

	@Override
	public List<M> getAll() {
		if (this.results == null) {
			this.info("Getting all");
			try {
				final PartialResults<M> page = this.getFirstPage();
				this.results = page.getAll();
				this.totalCount = page.getTotalCount();
			} catch (final LocaleViewableTempleException e) {
				this.addErrorMessage(e);
				this.results = null;
				this.totalCount = 0;
			} finally {
				this.resetPageCount();
			}
		}
		return this.results;
	}

	protected abstract PartialResults<M> getFirstPage() throws LocaleViewableTempleException;

	@Override
	public M getResult(int index) {
		if (this.results == null) {
			return null;
		}
		return this.results.isEmpty() ? null : this.results.get(index % this.results.size());
	}

	@Override
	public int getPage() {
		return this.filter.getPage();
	}

	@Override
	public void setPage(int page) {
		this.filter.setPage(page > this.pageCount ? page % this.pageCount : page);
	}

	@Override
	public void nextPage() {
		this.setPage(this.getPage() + 1);
	}

	@Override
	public void previousPage() {
		this.setPage(this.getPage() - 1);
	}

	@Override
	public int getPageCount() {
		return this.pageCount;
	}

	@Override
	public short getPerPageCount() {
		return this.filter.getPerPageCount();
	}

	@Override
	public void setPerPageCount(short perPageCount) {
		if (this.filter.getPerPageCount() != perPageCount) {
			this.filter.setPerPageCount(perPageCount);
			this.resetPageCount();
		}
	}

	private void resetPageCount() {
		final short maxCount = this.filter.getPerPageCount();
		this.pageCount = this.totalCount / maxCount + (this.totalCount % maxCount > 0 ? 1 : 0);
	}
}
