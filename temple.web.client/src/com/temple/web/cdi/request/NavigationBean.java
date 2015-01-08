package com.temple.web.cdi.request;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.temple.cdi.ApplicationBean;
import com.temple.cdi.TempleBean;
import com.temple.web.cdi.WebConfiguration;
import com.temple.web.cdi.WebRequestParameter;
import com.temple.web.cdi.WebRequestParameter.Type;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Model
@TempleBean
public class NavigationBean extends AbstractRequestBean {

	@Inject
	@ApplicationBean
	private WebConfiguration webConfig;

	@Inject
	@WebRequestParameter(type = Type.PAGE)
	private Instance<String> currentpage;

	/**
	 * @return the current page
	 */
	public String getCurrentPage() {
		return this.currentpage.get();
	}

	/**
	 * TODOC
	 *
	 * @return
	 */
	public String getTitleKey() {
		// TODO cache titles key ??
		final String cp = this.getCurrentPage();
		return this.webConfig.getCommonPages().contains(cp) ? cp + "_title" : null;
	}
}
