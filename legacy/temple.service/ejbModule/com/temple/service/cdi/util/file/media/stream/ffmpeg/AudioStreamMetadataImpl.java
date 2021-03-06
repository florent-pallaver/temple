package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.ToString;
import com.temple.util.file.media.stream.AudioStreamMetadata;

final class AudioStreamMetadataImpl extends AbstractStreamMetadata implements AudioStreamMetadata {

	private static final long serialVersionUID = 1L;

	@ToString
	private final Integer sampleRate;

	@ToString
	private final int channels;

	AudioStreamMetadataImpl(FFProbeMetadata ffp) {
		super(ffp.format, ffp.streams.audio);
		this.sampleRate = ffp.streams.audio.sample_rate;
		this.channels = ffp.streams.audio.channels;
	}

	@Override
	public Integer getSampleRate() {
		return this.sampleRate;
	}

	@Override
	public int getChannels() {
		return this.channels;
	}
}
