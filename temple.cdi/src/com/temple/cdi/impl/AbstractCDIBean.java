package com.temple.cdi.impl;

import com.temple.Module;
import com.temple.bean.AbstractTempleBean;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class AbstractCDIBean extends AbstractTempleBean {

	/**
	 * Constructor.
	 */
	protected AbstractCDIBean() {
		super(Module.CDI);
	}
}
