package com.temple;

import com.temple.util.TempleUtil;

/**
 * Class implementing {@link #hashCode()}, {@link #equals(Object)} and {@link #toString()} using {@link #naturalKey()}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class TempleObject {

	@Override
	public int hashCode() {
		return TempleUtil.hashCode(this.naturalKey());
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || this.getClass().isInstance(obj) && TempleUtil.equals(this.naturalKey(), ((TempleObject) obj).naturalKey());
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}

	/**
	 * An object identifying this object. It should remain constant over time.
	 * 
	 * @return an object (may be an array) identifying this object..
	 */
	protected abstract Object naturalKey();
}
