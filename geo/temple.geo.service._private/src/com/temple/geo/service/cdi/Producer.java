package com.temple.geo.service.cdi;

import javax.enterprise.context.ApplicationScoped;
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
@ApplicationScoped
public class Producer extends AbstractCDIBean {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "com.temple.geo.model.persistenceUnit")
	private EntityManager em;

	@Produces
	@Dependent
	@TempleObject
	EntityManager createEntityManager() {
		if (this.isInfoLoggable()) {
			this.info("Creating EntityManager");
		}
		return this.em;
	}

}
