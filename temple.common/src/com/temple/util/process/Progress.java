package com.temple.util.process;

import com.temple.Module;
import com.temple.impl.view.DefaultLocaleViewable;
import com.temple.view.LocaleBundle;
import java.io.Serializable;

/**
 * Util class to provide information about an operation progress.
 * 
 * @author flominou
 * @version 1.0
 */
public class Progress extends DefaultLocaleViewable {
	
	private static final long serialVersionUID = 1L ;
	
	public static final String DEFAULT_LOCALE_KEY = Progress.class.getName() ;
	
	public static final String PERCENT_LOCALE_KEY = Progress.class.getName() + DELIMITER + "percent" ;
	
	private int processed ;

	private final int toProcess ;
	
	private final long startTime ;
	
	private long endTime ;
	
	/**
	 * Constructor.
	 */
	public Progress() {
		this(0) ;
	}
	
	/**
	 * Constructor.
	 * TODOC
	 * @param toProcess
	 */
	public Progress(int toProcess) {
		this(toProcess, toProcess == 0 ? DEFAULT_LOCALE_KEY : PERCENT_LOCALE_KEY, Module.DEFAULT) ;
	}

	/**
	 * Constructor.
	 * TODOC
	 * @param infoKey
	 * @param module
	 */
	public Progress(String infoKey, LocaleBundle module) {
		this(0, infoKey, module);
	}
	
	/**
	 * Constructor.
	 * TODOC
	 * @param toProcess
	 * @param infoKey
	 * @param module
	 */
	public Progress(int toProcess, String infoKey, LocaleBundle module) {
		super(infoKey, new Serializable[2], module);
		this.processed = 0 ;
		this.toProcess = toProcess ;
		this.startTime = System.currentTimeMillis() ;
		this.endTime = 0 ;
	}
	
	/**
	 * TODOC
	 */
	public void incrementProcessed() {
		if(this.endTime == 0) {
			this.processed++ ;
		}
	}
	
	/**
	 * TODOC
	 */
	public void done() {
		this.endTime = System.currentTimeMillis() ;
	}

	/** 
	 * TODOC
	 * @return 
	 */
	public int getProcessingTime() {
		return (int) (((this.endTime == 0 ? System.currentTimeMillis() : this.endTime) - this.startTime) / 1000) ;
	}
	
	@Override
	public Serializable[] getLocaleParameters() {
		final Serializable[] lp = super.getLocaleParameters(); 
		lp[0] = toProcess == 0 ? this.processed : (this.processed * 100 / this.toProcess) ;
		lp[1] = this.getProcessingTime() ;
		return lp ;
	}
	
}
