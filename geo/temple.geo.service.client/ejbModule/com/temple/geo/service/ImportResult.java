package com.temple.geo.service;

import java.util.HashMap;
import java.util.Map;

import com.temple.util.process.Progress;

/**
 * TODOC
 *
 * @author flominou
 */
public class ImportResult extends Progress {

	private static final long serialVersionUID = 1L;

	private final Map<String, Exception> failedEntries = new HashMap<>();

	/**
	 * TODOC
	 * @return
	 */
	public Map<String, Exception> getFailedEntries() {
		return this.failedEntries;
	}

	/**
	 * TODOC
	 * @param entry
	 * @param cause
	 */
	public void addFailedEntry(String entry, Exception cause) {
		this.failedEntries.put(entry, cause) ;
	}

}
