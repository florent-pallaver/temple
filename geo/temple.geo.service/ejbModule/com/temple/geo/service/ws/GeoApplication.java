package com.temple.geo.service.ws;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(GeoApplication.RESOURCE_ROOT_PATH)
public class GeoApplication extends Application {

	/**
	 * TODOC
	 */
	public static final String RESOURCE_ROOT_PATH = "webapi";

}
