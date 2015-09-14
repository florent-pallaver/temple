package com.temple.service.cdi.application;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.temple.model.TempleUser;
import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.TempleObject;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
// TOTHINK See if can use interceptor or such
@ApplicationScoped
@TempleObject
public class ApplicationManagerBean extends AbstractCDIBean implements ApplicationManager {

	private static final long serialVersionUID = 1L;

	private final Map<Integer, TempleUser> signedInUsers = new HashMap<Integer, TempleUser>();

	ApplicationManagerBean() {
		super();
	}

	@Override
	public void userSignedIn(TempleUser u) {
		this.signedInUsers.put(u.getId(), u);
	}

	@Override
	public void userSignedOut(Integer userId) {
		this.signedInUsers.remove(userId);
	}
}
