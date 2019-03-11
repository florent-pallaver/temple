package com.temple.geo.service.cdi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.TempleObject;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Dependent
public class Producer extends AbstractCDIBean {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "com.temple.geo.model.persistenceUnit")
	private EntityManager em;

	@Produces
	@TempleObject
	EntityManager createEntityManager() {
		if (this.isDebugLoggable()) {
			this.debug(this.em == null ? "em null" : "em not ull");
		}
		return this.em;
	}

}
