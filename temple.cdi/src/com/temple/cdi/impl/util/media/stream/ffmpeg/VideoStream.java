package com.temple.cdi.impl.util.media.stream.ffmpeg;

import com.temple.util.ToString;
import com.temple.util.json.IntegerHandler;
import com.temple.util.json.JsonField;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
final class VideoStream extends AbstractStream {

	@ToString
	@JsonField(handler = IntegerHandler.class)
	int width;

	@ToString
	@JsonField(handler = IntegerHandler.class)
	int height;

	@ToString
	@JsonField
	String sample_aspect_ratio;

	@ToString
	@JsonField
	String display_aspect_ratio;

	@ToString
	@JsonField
	String r_frame_rate;
}
