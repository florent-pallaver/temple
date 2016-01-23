package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.json.AbstractJsonable;
import com.temple.util.json.JsonField;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
abstract class AbstractStream extends AbstractJsonable {

	@ToString
	@JsonField(handler = IntegerHandler.class)
	Integer size;

	@ToString
	@JsonField
	String codec_name;

	@ToString
	@JsonField
	String codec_long_name;

	//
	// @ToString
	// @JsonField
	// String profile;
	// @ToString
	// @JsonField(handler = DoubleHandler.class)
	// String start_time;
	@ToString
	@JsonField(handler = IntegerHandler.class)
	Integer bit_rate;

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
