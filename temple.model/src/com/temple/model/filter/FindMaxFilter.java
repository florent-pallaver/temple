package com.temple.model.filter;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface FindMaxFilter<F extends Serializable> extends Serializable {

	/**
	 * TODOC
	 * 
	 * @param em
	 * @return
	 */
	TypedQuery<F> createMaxQuery(EntityManager em);
}
