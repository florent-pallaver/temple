package com.temple.cdi.impl.util.media.stream.ffmpeg;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
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
