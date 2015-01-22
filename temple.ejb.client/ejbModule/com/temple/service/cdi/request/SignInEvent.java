package com.temple.service.cdi.request;

import java.io.Serializable;

import com.temple.model.TempleUser;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class SignInEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private TempleUser user;

	/**
	 * Constructor.
	 */
	public SignInEvent(TempleUser user) {
		super();
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public TempleUser getUser() {
		return this.user;
	}
}
