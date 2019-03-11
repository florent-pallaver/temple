/**
 *
 */
package com.temple.geo.service.cdi.session;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.temple.impl.util.human.LanguageEnum;
import com.temple.service.cdi.ApplicationBean;
import com.temple.service.cdi.session.AbstractLanguageBean;

/**
 * @author flominou
 *
 */
@Named
@SessionScoped
@ApplicationBean
public class LanguageBean extends AbstractLanguageBean {

	private static final long serialVersionUID = 1L;

	LanguageBean() {
		super(LanguageEnum.ENGLISH);
	}

}
