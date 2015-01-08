package com.temple.util.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
// TODO add TYPE support
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonField {

	/**
	 * TODOC
	 *
	 * @return
	 */
	Class<? extends Handler> handler() default StringHandler.class;

	/**
	 * @return <code>true</code> if this property can be overwritten, <code>false</code> if it cannot. Default to
	 *         <code>true</code>.
	 */
	boolean inputable() default true;

	/**
	 * @return <code>true</code> if this property can be sent, <code>false</code> if it cannot. Default to
	 *         <code>true</code>.
	 */
	boolean outputable() default true;

	/**
	 * TODOC {@link Handler}s implementation must be public
	 * 
	 * @author Florent Pallaver
	 * @version 1.0
	 */
	public static interface Handler {

		/**
		 * TODOC
		 *
		 * @param job
		 * @param name
		 * @param value
		 */
		void add(JsonObjectBuilder job, String name, Object value);

		/**
		 * TODOC
		 *
		 * @param jo
		 * @param name
		 * @return
		 */
		Object getValue(JsonObject jo, String name);
	}
}
