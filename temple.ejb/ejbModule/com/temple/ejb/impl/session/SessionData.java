package com.temple.ejb.impl.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.temple.Module;
import com.temple.bean.AbstractTempleBean;
import com.temple.cdi.CDISessionParameter;
import com.temple.cdi.CDISessionParameter.Type;
import com.temple.cdi.TempleBean;
import com.temple.cdi.application.ApplicationManager;
import com.temple.model.TempleUser;
import com.temple.util.human.Language;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@SessionScoped
class SessionData extends AbstractTempleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleBean
	private ApplicationManager am;

	private Language language;

	private TempleUser user = null;

	private final Map<String, Object> parameters = new HashMap<>();

	SessionData() {
		super(Module.CDI);
	}

	@Produces
	@Dependent
	@CDISessionParameter(Type.USER)
	TempleUser getUser() {
		return this.user;
	}

	void setUser(TempleUser user) {
		if (user != null) {
			this.am.userSignedIn(user);
		} else if (this.user != null) {
			this.am.userSignedOut(this.user.getId());
		}
		this.user = user;
		this.parameters.clear();
	}

	Language getLanguage() {
		return this.language;
	}

	void setLanguage(Language language) {
		this.language = language;
	}

	Object get(String key) {
		return this.parameters.get(key);
	}

	Object set(String key, Object value) {
		return this.parameters.put(key, value);
	}

	@Produces
	@Dependent
	@CDISessionParameter(Type.SIGNED_IN)
	boolean isSignedIn() {
		return this.user != null;
	}

	@PreDestroy
	@Override
	protected void preDestroy() {
		super.preDestroy();
		this.setUser(null);
	}
}
