package com.temple.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author flominou
 */
@Embeddable
public class Counter implements Serializable {
	
	private static final long serialVersionUID = 1L ;
	
	@Column(nullable = false)
	private int count ;

	/**
	 * Constructor
	 */
	public Counter() {
		this.count = 0 ;
	}
	
	/**
	 * @return the count
	 */
	public int getCount() {
		return this.count ;
	}
	
	/**
	 * Increments the count
	 */
	public void increment() {
		this.count++ ;
	}
	
	/**
	 * Decrements the count
	 */
	public void decrement() {
		this.count-- ;
	}
	
}
