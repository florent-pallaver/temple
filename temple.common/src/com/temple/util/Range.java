package com.temple.util;

import java.io.Serializable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class Range<N extends Comparable<N>> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ToString
	private N lower;

	@ToString
	private N higher;

	@ToString
	private boolean includingLower;

	@ToString
	private boolean includingHigher;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param lower
	 * @param higher
	 * @throws IllegalArgumentException
	 */
	public Range(N lower, N higher) throws IllegalArgumentException {
		this(lower, higher, true, true);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param lower
	 * @param higher
	 * @param includingLower
	 * @param includingHigher
	 * @throws IllegalArgumentException
	 */
	public Range(N lower, N higher, boolean includingLower, boolean includingHigher) throws IllegalArgumentException {
		super();
		this.lower = lower;
		this.setHigher(higher);
		this.includingLower = includingLower;
		this.includingHigher = includingHigher;
	}

	/**
	 * TODOC
	 * 
	 * @return
	 */
	public boolean isDefined() {
		return this.lower != null && this.higher != null;
	}

	/**
	 * TODOC
	 * 
	 * @param n
	 * @return
	 */
	public boolean isInRange(N n) {
		return n != null && (this.lower == null || this.lessThan(this.lower, n, this.includingLower))
				&& (this.higher == null || this.lessThan(n, this.higher, this.includingHigher));
	}

	private boolean lessThan(N left, N right, boolean orEqual) {
		final int n = left.compareTo(right);
		return n < 0 || orEqual && n == 0;
	}

	/**
	 * @return the lower
	 */
	public N getLower() {
		return this.lower;
	}

	/**
	 * Sets the lower
	 * 
	 * @param lower the lower to set
	 */
	public void setLower(N lower) throws IllegalArgumentException {
		if (lower.compareTo(this.higher) > 0) {
			throw new IllegalArgumentException(lower + " is more than the higher limit of this range " + this.higher);
		}
		this.lower = lower;
	}

	/**
	 * @return the higher
	 */
	public N getHigher() {
		return this.higher;
	}

	/**
	 * Sets the higher
	 * 
	 * @param higher the higher to set
	 */
	public void setHigher(N higher) throws IllegalArgumentException {
		if (higher.compareTo(this.lower) < 0) {
			throw new IllegalArgumentException(higher + " is less than the lower limit of this range " + this.lower);
		}
		this.higher = higher;
	}

	/**
	 * @return the includingLower
	 */
	public boolean isIncludingLower() {
		return this.includingLower;
	}

	/**
	 * Sets the includingLower
	 * 
	 * @param includingLower the includingLower to set
	 */
	public void setIncludingLower(boolean includingLower) {
		this.includingLower = includingLower;
	}

	/**
	 * @return the includingHigher
	 */
	public boolean isIncludingHigher() {
		return this.includingHigher;
	}

	/**
	 * Sets the includingHigher
	 * 
	 * @param includingHigher the includingHigher to set
	 */
	public void setIncludingHigher(boolean includingHigher) {
		this.includingHigher = includingHigher;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
