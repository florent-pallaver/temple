package com.temple.web.validation;

import javax.faces.validator.Validator;

import com.temple.AbstractTempleBean;
import com.temple.Module;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractTempleValidatorBean extends AbstractTempleBean implements Validator {

	/**
	 * Constructor.
	 */
	protected AbstractTempleValidatorBean() {
		super(Module.WEB);
	}
}
