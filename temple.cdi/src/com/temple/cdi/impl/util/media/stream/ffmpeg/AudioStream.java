package com.temple.cdi.impl.util.media.stream.ffmpeg;

import com.temple.util.ToString;
import com.temple.util.json.JsonField;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
final class AudioStream extends AbstractStream {

	@ToString
	@JsonField(handler = IntegerHandler.class)
	Integer sample_rate;

	@ToString
	@JsonField(handler = com.temple.util.json.IntegerHandler.class)
	int channels;
}
