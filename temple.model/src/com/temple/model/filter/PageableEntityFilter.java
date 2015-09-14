package com.temple.model.filter;

import com.temple.model.TempleEntity;
import com.temple.util.Pageable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
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
