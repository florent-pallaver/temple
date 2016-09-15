package com.temple.service.ejb.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.temple.service.cdi.TempleObject;
import com.temple.service.ejb.AbstractEJBBean;

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
