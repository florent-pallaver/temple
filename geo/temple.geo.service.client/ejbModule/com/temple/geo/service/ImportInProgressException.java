package com.temple.geo.service;

import com.temple.view.LocaleViewable;

/**
 * Thrown whenever geo data are being imported and a geo service is unavailable.
 *
 * @author flominou
 */
public final class ImportInProgressException extends GeoServiceException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public ImportInProgressException() {
		super(LocaleViewable.NO_PARAMETERS);
	}

}
