package com.temple.web.validation;

import com.temple.util.calendar.CalendarUtil;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * Validator to use to make sure a given date is at least a certain age old. 
 * @author flominou
 */
//@FacesValidator("com.temple.MinimumAgeValidator")
public class MinimumAgeValidator extends AbstractTempleValidatorBean {

	public static final int DEFAULT_MIN_AGE = 18 ;
	
	private int min = DEFAULT_MIN_AGE;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value instanceof Date) {
			final Date d = (Date) value ;
			final int age = CalendarUtil.findAge(d);
			if(isDebugLoggable()) {
				this.debug("for date " + CalendarUtil.DATE_FORMAT.format(d) + " age is " + age) ;
			}
			if(age < this.min) {
				final FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR , "fuck", "fuck you !!");
				context.addMessage(component.getClientId(context), fm);
				throw new ValidatorException(fm) ;
			}
		}
	}
	
}
