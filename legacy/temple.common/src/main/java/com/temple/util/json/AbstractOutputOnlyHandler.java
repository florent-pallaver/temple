package com.temple.util.json;

import javax.json.JsonObject;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractOutputOnlyHandler extends AbstractHandler {

	@Override
	protected final Object getNullSafeValue(JsonObject jo, String name) {
		throw new UnsupportedOperationException("This handler is meant to be used for output only !") ;
	}
}
