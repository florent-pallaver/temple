package com.temple.web.validation;

import javax.faces.validator.Validator;

import com.temple.Module;
import com.temple.bean.AbstractTempleBean;

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
