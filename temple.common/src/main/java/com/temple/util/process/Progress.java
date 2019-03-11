package com.temple.util.process;

import java.io.Serializable;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import com.temple.util.json.AbstractOutputOnlyHandler;

/**
 *
 * @author flominou
 * @see JsonHandler
 */
public interface Progress extends Serializable {

	Date getStartDate();

	long getProcessed();

	/**
	 * Increments this progress by one unit.
	 */
	default void incrementProcessed() {
		this.incrementProcessed(1L);
	}

	/**
	 * Increments this progress by a given amount of unit.
	 *
	 * @param unitAmount
	 */
	void incrementProcessed(long unitAmount);

	default boolean hasStartedProcessing() {
		return this.getProcessed() > 0;
	}

	/**
	 * Mark this progress as done. Any subsequent call to
	 * {@link #incrementProcessed()} won't do anything.
	 */
	void done();

	boolean isDone();

	/**
	 * @return if {@link #isDone()} seconds since this progress started,
	 *         otherwise seconds until it was done.
	 */
	int getProcessingTime();

	/**
	 * @return if {@link #isDone()} the progressing speed, otherwise the
	 *         progressing speed so far.
	 */
	default int getProcessingSpeed() {
		final int processingTime = this.getProcessingTime();
		return processingTime == 0 ? 0 : Math.round(this.getProcessed() / processingTime);
	}

	public static final class JsonHandler extends AbstractOutputOnlyHandler {

		@Override
		protected void nullSafeAdd(JsonObjectBuilder job, String name, Object value) {
			final Progress progress = (Progress) value;
			final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("processed", progress.getProcessed());
			objectBuilder.add("speed", progress.getProcessingSpeed());
			objectBuilder.add("time", progress.getProcessingTime());
			objectBuilder.add("done", progress.isDone());
			objectBuilder.add("started", progress.hasStartedProcessing());
			job.add(name, objectBuilder.build());
		}

		@Override
		protected void nullSafeAdd(JsonArrayBuilder job, Object value) {
			final Progress progress = (Progress) value;
			final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			arrayBuilder.add(progress.getProcessed());
			arrayBuilder.add(progress.getProcessingSpeed());
			arrayBuilder.add(progress.getProcessingTime());
			arrayBuilder.add(progress.isDone());
			arrayBuilder.add(progress.hasStartedProcessing());
			job.add(arrayBuilder.build());
		}

	}

}