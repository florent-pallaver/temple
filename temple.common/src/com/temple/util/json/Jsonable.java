package com.temple.util.json;

import javax.json.JsonArray;
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
	default JsonObject toJsonObject() {
		return JsonUtil.toJsonObject(this) ;
	}

	/**
	 * @return the {@link JsonArray} corresponding to this object.
	 */
	default JsonArray toJsonArray() {
		return JsonUtil.toJsonArray(this) ;
	}

	/**
	 * By default not implemented (ie throws {@link UnsupportedOperationException}).
	 * TODOC
	 *
	 * @param jo
	 */
	default void setValues(JsonObject jo) {
		throw new UnsupportedOperationException() ;
	}
}
