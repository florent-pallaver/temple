package com.temple.web.cdi.application;

import com.temple.service.ServiceCaller;
import com.temple.service.ServiceException;
import com.temple.web.cdi.AbstractTempleWebBean;
import java.io.Serializable;
import javax.faces.event.FacesEvent;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractApplicationBean extends AbstractTempleWebBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * TODOC
	 * <br>
	 * Shouldn't be overridden
	 *
	 * @param sc
	 */
	protected void execute(ServiceCaller sc) {
		this.execute(sc, null);
	}

	/**
	 * TODOC
	 * <br>
	 * Shouldn't be overridden
	 *
	 * @param sc
	 * @param event - the event that triggered this service execution
	 * @see #serviceFailed(com.temple.service.ServiceException, javax.faces.event.FacesEvent) 
	 * @see #serviceCalled(javax.faces.event.FacesEvent) 
	 */
	protected void execute(ServiceCaller sc, FacesEvent event) {
		try {
			sc.call();
			this.serviceCalled(event);
		} catch (final ServiceException e) {
			this.serviceFailed(e, event);
		}
	}
	
	/**
	 * Called whenever the service has been called and hasn't thrown an exception
	 * <br>
	 * TODOC
	 * <br>
	 * Default implementation does nothing
	 * @param event
	 */
	protected void serviceCalled(FacesEvent event) {}
	
	/**
	 * Called by {@link AbstractApplicationBean#execute(com.temple.service.ServiceCaller, javax.faces.event.FacesEvent) execute} 
	 * if a {@link ServiceException} is thrown by the {@link ServiceCaller}.
	 * <br>
	 * Base implementation just logs the exception.
	 * @param e - the exception which caused the service to fail
	 * @param fe - the event which triggered the service call, maybe <code>null</code>
	 */
	protected void serviceFailed(final ServiceException e, FacesEvent fe) {
		this.thrown(e);
	}
}
