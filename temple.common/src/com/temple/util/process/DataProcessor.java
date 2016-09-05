package com.temple.util.process;

import java.util.concurrent.Callable;

/**
 * TODOC
 * @author flominou
 */
public interface DataProcessor<RESULT> extends Callable<RESULT> {

	@Override
	RESULT call() throws Exception ;

	/**
	 * @return the {@link LimitedProgress} of the current or last processing.
	 */
	Progress getProgress() ;

}
