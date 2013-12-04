package com.temple.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class IdentifierGeneratorHelper {

	public static final IdentifierGeneratorHelper instance = new IdentifierGeneratorHelper();

	/**
	 * TODOC
	 */
	private final Map<Class<?>, IdentifierGenerator> generators = new HashMap<>();

	private IdentifierGeneratorHelper() {}

	/**
	 * TODOC
	 * 
	 * @param c
	 * @param ig
	 */
	public final void register(Class<?> c, IdentifierGenerator ig) {
		this.generators.put(c, ig);
	}

	/**
	 * TODOC
	 * 
	 * @param e
	 * @return
	 */
	public final Serializable newId(TempleEntity e) {
		return this.generators.get(e.getClass()).newId(e);
	}
}
