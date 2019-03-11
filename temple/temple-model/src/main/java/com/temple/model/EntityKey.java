package com.temple.model;

import java.io.Serializable;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface EntityKey<E extends TempleEntity, ID extends Serializable> {

	Class<E> getEntityClass();

	Predicate[] createPredicates(CriteriaBuilder cb, Root<E> from, ID[] values);
}
