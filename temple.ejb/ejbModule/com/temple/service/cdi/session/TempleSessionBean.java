package com.temple.service.cdi.session;

import com.temple.model.TempleUser;
import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.CDISessionParameter;
import com.temple.service.cdi.TempleObject;
import com.temple.service.cdi.application.ApplicationManager;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Named("sessionBean")
@SessionScoped
@TempleObject
public class TempleSessionBean extends AbstractCDIBean implements SessionBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private ApplicationManager am;

	private TempleUser user = null;

	private final Map<String, Object> parameters = new HashMap<>();

	TempleSessionBean() {}

	@Override
	@Produces
	@Dependent
	@CDISessionParameter(CDISessionParameter.Type.USER)
	public TempleUser getUser() {
		return this.user;
	}

	@Override
	public void setUser(TempleUser user) {
		if (user != null) {
			this.am.userSignedIn(user);
		} else if (this.user != null) {
			this.am.userSignedOut(this.user.getId());
		}
		this.user = user;
		this.parameters.clear();
	}

	@Override
	public Object get(String key) {
		return this.parameters.get(key);
	}

	@Override
	public void set(String key, Object value) {
		this.parameters.put(key, value);
	}

	@Override
	@Produces
	@Dependent
	@CDISessionParameter(CDISessionParameter.Type.SIGNED_IN)
	public boolean isSignedIn() {
		return this.user != null;
	}

	@PreDestroy
	@Override
	protected void preDestroy() {
		super.preDestroy();
		this.setUser(null);
	}
}
