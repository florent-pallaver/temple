package com.temple.ejb.impl.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.temple.cdi.CDIRequestParameter;
import com.temple.cdi.CDIRequestParameter.Type;
import com.temple.ejb.impl.AbstractManagerBean;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractEntityManagerBean extends AbstractManagerBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@CDIRequestParameter(Type.ENTITY_MANAGER)
	protected EntityManager em;

	protected CriteriaBuilder cb;

	@PostConstruct
	@Override
	protected void postConstruct() {
		this.cb = this.em.getCriteriaBuilder();
		super.postConstruct();
	}

	/**
	 * TODOC
	 * 
	 * @author Florent Pallaver
	 * @version 1.0
	 * @param <X>
	 */
	protected static interface WhereAlgo<X extends Serializable> {

		/**
		 * TODOC
		 * 
		 * @param cb
		 * @param root
		 */
		Predicate getWherePredicate(CriteriaBuilder cb, Path<X> root);
	}
}
