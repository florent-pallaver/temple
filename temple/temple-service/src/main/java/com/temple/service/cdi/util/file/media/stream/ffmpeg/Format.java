package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.ToString;
import com.temple.util.json.JsonField;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
final class Format extends AbstractStream {

	@ToString
	@JsonField(handler = com.temple.util.json.IntegerHandler.class)
	int nb_streams;

	@ToString
	@JsonField
	String format_name;

	@ToString
	@JsonField
	String format_long_name;

	// @JsonField(handler = DoubleHandler.class)
	// String start_time;
	@ToString
	@JsonField(handler = DoubleHandler.class)
	Double duration;
}
