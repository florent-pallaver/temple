package com.temple.geo.service;

import com.temple.service.ServiceException;
import com.temple.util.file.Content;
import com.temple.util.process.DataProcessor;

/**
 * Base contract for a class to be used as a geographic data importer.
 * <br>
 * This interface aims at allowing to import raw geographic data to a database.
 * @author flominou
 * @version 1.0
 */
public interface GeoDataImporter extends DataProcessor {

	/**
	 * Asynchronously resets and imports geographic data thanks to a given raw geographic data resource.
	 * @param rawGeoData the resource containing raw geographic data
	 * @param limit the maximum count of entries to process, <code>0</code> if all entries has to be processed
	 * @throws ServiceException
	 */
	void  importData(Content rawGeoData, long limit) throws ServiceException;

}
