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

	//	@PersistenceUnit(unitName = "com.temple.geo.model.persistenceUnit")
	//	private EntityManagerFactory emf ;

	@PersistenceContext(unitName = "com.temple.geo.model.persistenceUnit")
	private EntityManager em;

	@Produces
	@TempleObject
	EntityManager createEntityManager() {
		if (this.isInfoLoggable()) {
			this.info(this.em == null ? "em null" : "em not null");
		}
		return this.em;
	}

}
