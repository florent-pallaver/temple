package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.ToString;
import com.temple.util.json.JsonField;

final class AudioStream extends AbstractStream {

	@ToString
	@JsonField
	String sample_rate;

	@ToString
	@JsonField(handler = IntegerHandler.class)
	int channels;
}
