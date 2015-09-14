package com.temple.service.ejb.model;

import com.temple.service.cdi.TempleObject;
import com.temple.service.ejb.AbstractEJBBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractEntityManagerBean extends AbstractEJBBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
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
	@FunctionalInterface
	protected static interface WhereAlgo<X extends Serializable> {

		/**
		 * TODOC
		 *
		 * @param cb
		 * @param root
		 * @return 
		 */
		Predicate getWherePredicate(CriteriaBuilder cb, Path<X> root);
	}
}
