package com.temple.util.json;

import javax.json.JsonObject;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class AbstractJsonable implements Jsonable {

	/**
	 * Constructor.
	 */
	protected AbstractJsonable() {}

	/**
	 * Constructor.
	 * TODOC <br />
	 * null safe
	 *
	 * @param jo
	 */
	protected AbstractJsonable(JsonObject jo) {
		this();
		if (jo != null) {
			this.setValues(jo);
		}
	}

	@Override
	public void setValues(JsonObject jo) {
		JsonUtil.setValues(this, jo);
	}
}
