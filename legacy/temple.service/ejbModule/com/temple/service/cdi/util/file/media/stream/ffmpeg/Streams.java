package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;

final class Streams {

	@ToString
	AudioStream audio;

	@ToString
	VideoStream video;

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
