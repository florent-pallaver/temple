package com.temple.util.json;

import javax.json.JsonObject;

/**
 * Base contract to translate an object to its JSon representation.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Jsonable {

	/**
	 * @return the {@link JsonObject} corresponding to this object.
	 */
	JsonObject toJsonObject();

	/**
	 * TODOC
	 * 
	 * @param jo
	 */
	void setValues(JsonObject jo);
}
