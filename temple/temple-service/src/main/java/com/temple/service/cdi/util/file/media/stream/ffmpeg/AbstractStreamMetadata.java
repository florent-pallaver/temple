package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.file.media.stream.StreamMetadata;

abstract class AbstractStreamMetadata implements StreamMetadata {

	private static final long serialVersionUID = 1L;

	@ToString
	private final String codecName;

	@ToString
	private final String codecLongName;

	// @ToString
	// private String profile;
	@ToString
	private final int bitRate;

	@ToString
	private final double preciseDuration;

	@ToString
	private final long size;

	AbstractStreamMetadata(Format f, AbstractStream stream) {
		this(f, stream, false);
	}

	AbstractStreamMetadata(Format f, AbstractStream stream, boolean format) {
		super();
		this.codecName = format ? f.format_name : stream.codec_name;
		this.codecLongName = format ? f.format_long_name : stream.codec_long_name;
		this.bitRate = stream.bit_rate == null ? 0 : stream.bit_rate;
		this.preciseDuration = f.duration == null ? 0 : f.duration;
		this.size = stream.size == null ? 0 : stream.size;
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
	public int getBitRate() {
		return this.bitRate;
	}

	@Override
	public double getPreciseDuration() {
		return this.preciseDuration;
	}

	@Override
	public long getSize() {
		return this.size;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
