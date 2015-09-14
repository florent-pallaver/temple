/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temple.util;

import java.util.Objects;

/**
 * Base contract to defines an interval.
 * <br>
 * It is implementation specific to define if the boundaries are included or not.
 * @author flominou
 * @param <D> 
 */
public interface Interval<D extends Comparable<D>> {
	
	/**
	 * @return the lower boundary of this interval
	 */
	D getLowerLimit();
	
	/**
	 * @return the higher boundary of this interval
	 */
	D getUpperLimit();
	
	/**
	 * Default implementation always returns <code>true</code>.
	 * @return <code>true</code> if the lower limit is included in this interval, <code>false</code> otherwise.
	 */
	default boolean includesLower() {
		return true ;
	}
	
	/**
	 * Default implementation always returns <code>true</code>.
	 * @return <code>true</code> if the upper limit is included in this interval, <code>false</code> otherwise.
	 */
	default boolean includesUpper() {
		return true ;
	}
	
	/**
	 * @param point a point
	 * @return <code>true</code> if the point is inside this interval, <code>false</code> otherwise.
	 * @throws NullPointerException if point is <code>null</code>.
	 */
	default boolean isIn(D point) {
		Objects.requireNonNull(point) ;
		return (this.getLowerLimit() == null || Util.lessThan(this.getLowerLimit(), point, this.includesLower()))
				&& (this.getUpperLimit() == null || Util.lessThan(point, this.getUpperLimit(), this.includesUpper()));
	}
	
	/**
	 * @return <code>true</code> if at least one boundary is defined, <code>false</code> if no boundaries are.
	 */
	default boolean isDefined() {
		return this.getLowerLimit() != null || this.getUpperLimit() != null;
	}

	/**
	 * 
	 */
	public static final class Util {

		private Util() {}

		/**
		 * 
		 * @param <C>
		 * @param lower
		 * @param higher 
		 * @throws IllegalArgumentException
		 */
		public static final <C extends Comparable<C>> void checkBoundaries(C lower, C higher) {
			if(lower != null && higher != null && lower.compareTo(higher) > 0) {
				throw new IllegalArgumentException(lower + " is more than the upper limit of this range " + higher);
			} 
		}
		
		/**
		 * 
		 * @param <C>
		 * @param toTest
		 * @param upper
		 * @param orEqual
		 * @return 
		 */
		public static final <C extends Comparable<C>> boolean lessThan(C toTest, C upper, boolean orEqual) {
			final int n = toTest.compareTo(upper);
			return n < 0 || orEqual && n == 0;
		}

	}
	
}

