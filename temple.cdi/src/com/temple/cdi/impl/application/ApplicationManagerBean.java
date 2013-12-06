package com.temple.cdi.impl.application;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.temple.cdi.TempleBean;
import com.temple.cdi.application.ApplicationManager;
import com.temple.cdi.impl.AbstractCDIBean;
import com.temple.model.TempleUser;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
// TOTHINK See if can use interceptor or such
@ApplicationScoped
@TempleBean
public class ApplicationManagerBean extends AbstractCDIBean implements ApplicationManager {

	private final Map<Integer, TempleUser> signInUsers = new HashMap<Integer, TempleUser>();

	ApplicationManagerBean() {
		super();
	}

	@Override
	public void userSignedIn(TempleUser u) {
		this.signInUsers.put(u.getId(), u);
	}

	@Override
	public void userSignedOut(Integer userId) {
		this.signInUsers.remove(userId);
	}
}
