package com.temple.model.impl;

import java.io.Serializable;

import javax.persistence.criteria.Predicate;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 */
public interface PredicateCreator {

	/**
	 * TODOC
	 * 
	 * @param serializable
	 * @return
	 */
	Predicate createPredicate(Serializable serializable);
}
