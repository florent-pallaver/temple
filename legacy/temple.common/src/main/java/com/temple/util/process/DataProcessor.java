package com.temple.util.process;

import java.util.concurrent.Callable;

public interface DataProcessor<RESULT> extends Callable<RESULT> {

	@Override
	RESULT call() throws Exception;

	/**
	 * @return the {@link LimitedProgress} of the current or last processing,
	 *         never <code>null</code>.
	 */
	Progress getProgress();

	/**
	 * Cancels this processor.
	 */
	void cancel();

	default boolean isDone() {
		return this.getProgress().isDone();
	}

}
