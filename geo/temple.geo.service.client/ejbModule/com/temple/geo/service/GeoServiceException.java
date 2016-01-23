package com.temple.geo.service;

import java.io.Serializable;

import com.temple.service.ServiceException;
import com.temple.view.LocaleBundle;

/**
 *
 * @author flominou
 */
public class GeoServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	protected GeoServiceException(Throwable cause) {
		super(cause);
	}

	protected GeoServiceException(Serializable[] parameters) {
		super(parameters);
	}

	protected GeoServiceException(Serializable[] parameters, Throwable cause) {
		super(parameters, cause);
	}

	protected GeoServiceException(LocaleBundle bundle, Throwable cause) {
		super(bundle, cause);
	}

	protected GeoServiceException(LocaleBundle bundle, Serializable[] parameters) {
		super(bundle, parameters);
	}

	protected GeoServiceException(LocaleBundle bundle, Serializable[] parameters, Throwable cause) {
		super(bundle, parameters, cause);
	}

}
