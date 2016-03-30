package com.temple.service.cdi.request;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import com.temple.model.TempleUser;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class SignEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private final TempleUser user;

	/**
	 * Constructor.
	 */
	public SignEvent(TempleUser user) {
		super();
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public TempleUser getUser() {
		return this.user;
	}

	/**
	 * Qualifier for Sign In Event
	 *
	 * @author Florent Pallaver
	 * @version 1.0
	 */
	@Qualifier
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER })
	public @interface In {

	}

	/**
	 * Qualifier for Sign Out Event
	 *
	 * @author Florent Pallaver
	 * @version 1.0
	 */
	@Qualifier
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER })
	public @interface Out {

	}

}
