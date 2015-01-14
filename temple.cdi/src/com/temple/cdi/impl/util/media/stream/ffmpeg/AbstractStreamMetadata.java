package com.temple.cdi.impl.util.media.stream.ffmpeg;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.file.media.stream.StreamMetadata;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
abstract class AbstractStreamMetadata implements StreamMetadata {

	private static final long serialVersionUID = 1L;

	@ToString
	private final String codecName;

	@ToString
	private final String codecLongName;

	// @ToString
	// private String profile;
	@ToString
	private final Integer bitRate;

	@ToString
	private final Double preciseDuration;

	@ToString
	private final Integer size;

	AbstractStreamMetadata(Format f, AbstractStream stream) {
		this(f, stream, false);
	}

	AbstractStreamMetadata(Format f, AbstractStream stream, boolean format) {
		super();
		this.codecName = format ? f.format_name : stream.codec_name;
		this.codecLongName = format ? f.format_long_name : stream.codec_long_name;
		this.bitRate = stream.bit_rate;
		this.preciseDuration = f.duration;
		this.size = stream.size;
	}

	@Override
	public String getCodecName() {
		return this.codecName;
	}

	@Override
	public String getCodecLongName() {
		return this.codecLongName;
	}

	@Override
	public Integer getBitRate() {
		return this.bitRate;
	}

	@Override
	public Double getPreciseDuration() {
		return this.preciseDuration;
	}

	@Override
	public Integer getDuration() {
		return this.preciseDuration != null ? this.preciseDuration.intValue() : null;
	}

	@Override
	public Integer getSize() {
		return this.size;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
