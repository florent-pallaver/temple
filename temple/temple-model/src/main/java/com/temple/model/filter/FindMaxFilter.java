package com.temple.model.filter;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Deprecated
public interface FindMaxFilter<F extends Serializable> extends Serializable {

	TypedQuery<F> createMaxQuery(EntityManager em);
}
