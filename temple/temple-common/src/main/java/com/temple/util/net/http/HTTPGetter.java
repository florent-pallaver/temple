package com.temple.util.net.http;

import java.io.Serializable;
import java.net.URL;
import java.nio.file.attribute.PosixFilePermission;
import java.util.EnumSet;

public interface HTTPGetter<F extends Serializable> {

	String HTTP_PROTOCOL = "http";

	String HTTPS_PROTOCOL = "https";

	EnumSet<PosixFilePermission> DEFAULT_PERMS = EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE);
	
	int DEFAULT_READ_TIMEOUT = 15000; // in milliseconds

	Cookie getCookie() ;

	F getFile(String fileURL) throws HTTPGetException ;

	default F getFile(URL url) throws HTTPGetException {
		return this.getFile(url.toString());
	}
	
	int getReadTimeout();

	void setReadTimeout(int timeout);

}
