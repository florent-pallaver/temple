package com.temple.model.filter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.temple.model.TempleEntity;
import com.temple.util.Pageable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface PageableEntityFilter<E extends TempleEntity> extends EntityFilter<E>, Pageable {

	/**
	 * TODOC
	 *
	 * @param em
	 * @return
	 */
	TypedQuery<Long> createCountQuery(EntityManager em);
}
