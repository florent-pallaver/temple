package com.temple.model.filter;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.temple.model.TempleEntity;
import com.temple.util.Pageable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 */
public interface PageableEntityFilter<E extends TempleEntity, R extends Serializable>
		extends EntityFilter<E, R>, Pageable {

	/**
	 * TODOC
	 *
	 * @param em
	 * @return
	 */
	TypedQuery<Long> createCountQuery(EntityManager em);
}
