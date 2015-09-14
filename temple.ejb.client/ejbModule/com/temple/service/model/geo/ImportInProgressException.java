package com.temple.service.model.geo;

/**
 * Thrown whenever geo data are being imported and a geo service is unavailable.
 * 
 * @author flominou
 */
public final class ImportInProgressException extends GeoServiceException {

	/**
	 * Constructor
	 */
	public ImportInProgressException() {
		super(NO_PARAMETERS);
	}
	
}
