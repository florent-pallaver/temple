package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.ToString;
import com.temple.util.file.media.stream.VideoStreamMetadata;

final class VideoStreamMetadataImpl extends AbstractStreamMetadata implements VideoStreamMetadata {

	private static final long serialVersionUID = 1L;

	@ToString
	private final short width;

	@ToString
	private final short height;

	@ToString
	private final String sampleAspectRatio;

	@ToString
	private final String displayAspectRatio;

	@ToString
	private final String rFrameRate;

	VideoStreamMetadataImpl(FFProbeMetadata ffp) {
		super(ffp.format, ffp.streams.video);
		this.width = (short) ffp.streams.video.width;
		this.height = (short) ffp.streams.video.height;
		this.sampleAspectRatio = ffp.streams.video.sample_aspect_ratio;
		this.displayAspectRatio = ffp.streams.video.display_aspect_ratio;
		this.rFrameRate = ffp.streams.video.r_frame_rate;
	}

	@Override
	public short getWidth() {
		return this.width;
	}

	@Override
	public short getHeight() {
		return this.height;
	}

	@Override
	public String getSampleAspectRatio() {
		return this.sampleAspectRatio;
	}

	@Override
	public String getDisplayAspectRatio() {
		return this.displayAspectRatio;
	}

	@Override
	public String getRFrameRate() {
		return this.rFrameRate;
	}
}
