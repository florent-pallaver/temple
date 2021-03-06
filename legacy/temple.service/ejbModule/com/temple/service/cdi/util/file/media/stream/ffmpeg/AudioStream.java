package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.ToString;
import com.temple.util.json.JsonField;

final class AudioStream extends AbstractStream {

	@ToString
	@JsonField(handler = IntegerHandler.class)
	Integer sample_rate;

	@ToString
	@JsonField(handler = com.temple.util.json.IntegerHandler.class)
	int channels;
}
