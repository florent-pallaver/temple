package com.temple.web.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Named
@ApplicationScoped
public class IsTrueValidatorBean extends AbstractTempleValidatorBean {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (!Boolean.TRUE.equals(value)) {
			final String clientId = component.getClientId(context);
			// this.addErrorMessage(clientId, clientId + "_rq");
		}
	}
}
