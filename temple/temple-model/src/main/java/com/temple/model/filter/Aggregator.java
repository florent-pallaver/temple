package com.temple.model.filter;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.temple.model.TempleEntity;

public interface Aggregator<T extends TempleEntity, R extends Serializable> {
	
	Class<R> getAggregateClass();

	Selection<? extends R> createAggregatedSelection(CriteriaBuilder cb, Root<? extends T> root);
	
}