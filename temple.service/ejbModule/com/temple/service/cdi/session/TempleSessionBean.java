package com.temple.service.cdi.session;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.temple.model.TempleUser;
import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.CDISessionParameter;
import com.temple.service.cdi.TempleObject;
import com.temple.service.cdi.request.SignEvent;

@Named("sessionBean")
@SessionScoped
@TempleObject
public class TempleSessionBean extends AbstractCDIBean implements SessionBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@SignEvent.In
	private Event<SignEvent> signInEvent;

	@Inject
	@SignEvent.Out
	private Event<SignEvent> signOutEvent;

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
			this.signInEvent.fire(new SignEvent(user));
		} else if (this.user != null) {
			this.signOutEvent.fire(new SignEvent(this.user));
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
