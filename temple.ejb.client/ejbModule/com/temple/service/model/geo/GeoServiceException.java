package com.temple.service.model.geo;

import com.temple.service.ServiceException;
import com.temple.view.LocaleBundle;
import java.io.Serializable;

/**
 *
 * @author flominou
 */
public class GeoServiceException extends ServiceException {

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
