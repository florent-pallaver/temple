package com.temple;

import java.util.logging.Logger;

import com.temple.view.LocaleBundle;

/**
 * Enumeration of the existing modules in this application.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public enum Module implements LocaleBundle {
	/**
	 * The default module.
	 */
	DEFAULT(null),
	/**
	 * The CDI module!
	 */
	CDI("cdi"),
	/**
	 * The Model module.
	 */
	MODEL("model"),
	/**
	 * The EJB module.
	 */
	EJB("ejb"),
	/**
	 * The web module.
	 */
	WEB("web"),
	/**
	 * The client module.
	 */
	CLIENT("client");

	/**
	 * The root package of every module.
	 */
	public static final String DEFAULT_ROOT_PACKAGE = "com.temple";

	/**
	 * @return the package name of this Module.
	 */
	public final String packageName;

	/**
	 * @return the base name of the default locale bundle of this Module.
	 */
	public final String bundleBaseName;

	/**
	 * @return the default {@link Logger} of this Module.
	 */
	public final Logger logger;

	private Module(String moduleName) {
		final String suffix = moduleName == null ? "" : '.' + moduleName;
		// FIXME change how the bundle are retrieved
		this.packageName = Config.get(Config.ROOT_PACKAGE, Module.DEFAULT_ROOT_PACKAGE) + suffix;
		this.bundleBaseName = LocaleBundle.BASE_NAME_PREFIX + suffix + '.' + LocaleBundle.DEFAULT_LOCALE_FILE_NAME;
		this.logger = Logger.getLogger(this.packageName);
	}

	@Override
	public String getBaseName() {
		return this.bundleBaseName;
	}

	/**
	 * @return the package name of this Module.
	 * @see #getPackageName()
	 */
	@Override
	public String toString() {
		return this.packageName;
	}
}
