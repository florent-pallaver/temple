package com.temple.model.impl;

import javax.persistence.AttributeConverter;

/**
 * TODOC <br>
 * Note: because of <a>https://bugs.eclipse.org/bugs/show_bug.cgi?id=414679</a>, concrete subclasses must explicitly
 * implement {@link AttributeConverter}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 */
public abstract class AbstractEnumsToIntConverter<E extends Enum<E>> extends AbstractEnumsToNumberConverter<E, Integer> {

	/**
	 * Constructor.
	 * @param all TODOC
	 */
	protected AbstractEnumsToIntConverter(E[] all) {
		super(all) ;
	}

	@Override
	protected Integer castMask(Long mask) {
		return mask.intValue() ;
	}

}