package com.temple.model.filter;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.temple.model.TempleEntity;
import com.temple.util.Pageable;

public interface PageableEntityFilter<E extends TempleEntity, R extends Serializable>
		extends EntityFilter<E, R>, Pageable {

	TypedQuery<Long> createCountQuery(EntityManager em);
}
