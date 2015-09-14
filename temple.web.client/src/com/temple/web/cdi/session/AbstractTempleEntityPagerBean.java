/**
 *
 */
package com.temple.web.cdi.session;

import com.temple.model.TempleEntity;
import com.temple.model.filter.PageableEntityFilter;
import com.temple.service.ServiceException;
import com.temple.web.cdi.TempleEntityPager;
import com.temple.web.cdi.WebRequestParameter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <M>
 * @param <F>
 */
public abstract class AbstractTempleEntityPagerBean<M extends TempleEntity, F extends PageableEntityFilter<M>> 
	extends AbstractSessionBean implements TempleEntityPager<M, F> {

	private static final long serialVersionUID = 1L;

	private static final Pattern pattern = Pattern.compile("\\D*(\\d+).*");
	
	protected F filter;

	protected int totalCount = 0;

	protected int pageCount = 0;

	protected List<? extends M> results = null;

	private transient int[] pages ;
	
	@Inject
	@WebRequestParameter(index = 1)
	private Instance<String> page ;
	
	@Inject
	@WebRequestParameter(index = 2)
	private Instance<Integer> itemId ;
	
	// For CDI
	AbstractTempleEntityPagerBean() {
	}
	
	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param filter
	 */
	protected AbstractTempleEntityPagerBean(F filter) {
		super();
		this.filter = filter;
	}

	@Override
	public F getFilter() {
		return this.filter;
	}

	@Override
	public List<? extends M> getAll() {
		this.ensureResults();
		return this.results;
	}

	private void checkPage() {
		final String pa = this.page.get();
		if(pa != null && !pa.isEmpty()) {
			final Matcher m = pattern.matcher(pa);
			if(m.matches()) {
				int p = Integer.valueOf(m.group(1)) ;
				if(p != this.getPage()) {
					this.setPage(p);
				}
			}
		}
	}
	
	private void ensurePageCount() {
		if(this.totalCount == 0) {
			try {
				this.totalCount = (int) this.getTotalCount() ;
			} catch (ServiceException ex) {
				this.addError(ex);
			} finally {
				this.resetPageCount();
			}
		}
	}
	
	private void ensureResults() {
		if (this.results == null) {
			if(isDebugLoggable()) {
				this.debug("Getting results ...");
			}
			try {
				this.results = this.getResults() ;
			} catch (final ServiceException e) {
				this.addError(e);
			}
		} else if(this.isInfoLoggable()) {
			this.info("Using cached results");
		}
	}
	
	protected abstract List<? extends M> getResults() throws ServiceException ;
	
	protected abstract long getTotalCount() throws ServiceException ;
	
	protected abstract void checkFilterChanged() ;
	
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
		this.filter.setPage(this.normalize(page));
		this.results = null ;
	}

	private int normalize(int page) {
		this.ensurePageCount();
		return page < 1 ? this.pageCount : (page > this.pageCount ? page % this.pageCount : page) ;
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

	public int getPreviousPage() {
		return this.normalize(this.getPage() - 1) ;
	}
	
	public int getNextPage() {
		return this.normalize(this.getPage() + 1) ;
	}
	
	public int[] getPages() {
		this.checkFilterChanged();
		this.ensurePageCount();
		this.checkPage();
		return this.pages ;
	}
	
	private void resetPageCount() {
		final short maxCount = this.filter.getPerPageCount();
		this.pageCount = this.totalCount == 0 ? 1 : this.totalCount / maxCount + (this.totalCount % maxCount > 0 ? 1 : 0);
		this.pages = new int[this.pageCount] ;
		for(int i = 1 ; i <= this.pageCount ; i++) {
			this.pages[i-1] = i ;
		}
	}
	
	public static void main(String[] args) {
		
		String[] ps = {"kjdsdmflkm1", "page2", "page-3", "p4", "5", "caca", "one more trip", 
			"page 5", "5 page 2", "6 6", "page 7 ", "page 9 page", "45lkfd59jkjkjk89" } ;
		
		for (String p : ps) {
			final Matcher m = pattern.matcher(p);
			System.out.println(m.matches() ? m.group(1) : "---") ;
		}
		
	}
	
}
