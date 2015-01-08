package com.temple.cdi.impl.session;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.temple.AbstractTempleBean;
import com.temple.Module;
import com.temple.cdi.CDISessionParameter;
import com.temple.cdi.CDISessionParameter.Type;
import com.temple.cdi.TempleBean;
import com.temple.cdi.application.ApplicationManager;
import com.temple.cdi.session.SessionBean;
import com.temple.model.TempleUser;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Named("sessionBean")
@SessionScoped
@TempleBean
public class TempleSessionBean extends AbstractTempleBean implements SessionBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleBean
	private ApplicationManager am;

	private TempleUser user = null;

	private final Map<String, Object> parameters = new HashMap<>();

	TempleSessionBean() {
		super(Module.CDI);
	}

	@Override
	@Produces
	@Dependent
	@CDISessionParameter(Type.USER)
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
	@CDISessionParameter(Type.SIGNED_IN)
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
