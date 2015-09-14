package com.temple.service.model.geo;

import com.temple.util.process.Progress;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author flominou
 */
public class ImportResult extends Progress {
	
	private final Map<String, Exception> failedEntries = new HashMap<>();

	public Map<String, Exception> getFailedEntries() {
		return failedEntries;
	}

	public void addFailedEntry(String entry, Exception cause) {
		this.failedEntries.put(entry, cause) ;
	}
	
}
