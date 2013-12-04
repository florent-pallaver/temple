package com.temple;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * TODOC
 * 
 * @author Florent
 */
@Deprecated
public abstract class Config {

	/**
	 * TODOC
	 */
	public static final String BASE_RESOURCE_FOLDER = "resource";

	/**
	 * TODOC
	 */
	public static final String ROOT_PACKAGE = "package.root";

	private static final Properties properties = new Properties();

	private Config() {}

	/**
	 * @see Properties#getProperty(String)
	 */
	public static final String get(String key) {
		return Config.properties.getProperty(key);
	}

	/**
	 * @see Properties#getProperty(String, String)
	 */
	public static final String get(String key, String defaultValue) {
		return Config.properties.getProperty(key, defaultValue);
	}

	static {
		try {
			final ResourceBundle bundle = ResourceBundle.getBundle(Config.BASE_RESOURCE_FOLDER + ".config");
			final Enumeration<String> keys = bundle.getKeys();
			while (keys.hasMoreElements()) {
				final String k = keys.nextElement();
				Config.properties.setProperty(k, bundle.getString(k));
			}
		} catch (final Exception e) {
			throw new Error("", e);
		}
	}
}
