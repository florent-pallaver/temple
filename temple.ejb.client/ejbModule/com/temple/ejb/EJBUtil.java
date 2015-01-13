package com.temple.ejb;

import java.util.Properties;
import java.util.logging.Level;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.temple.Module;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class EJBUtil {

	private static final InitialContext ic;
	static {
		InitialContext c = null;
		try {
			final Properties p = new Properties();
			p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			p.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming.client"); // TODO change that !
			p.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099");
			c = new InitialContext(p);
		} catch (final Exception e) {
			Module.SERVICE.logger.log(Level.SEVERE, "An error occured while trying to create an InitialContext.", e);
			System.exit(0);
		} finally {
			ic = c;
		}
	}

	private EJBUtil() {}

	/**
	 * @return an {@link InitialContext} to access this application's EJB's.
	 */
	public static final InitialContext getInitialContext() {
		return EJBUtil.ic;
	}
}
