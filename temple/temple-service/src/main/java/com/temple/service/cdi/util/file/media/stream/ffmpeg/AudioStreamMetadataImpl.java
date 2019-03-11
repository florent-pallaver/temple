package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.file.media.stream.AudioStreamMetadata;

final class AudioStreamMetadataImpl extends AbstractStreamMetadata implements AudioStreamMetadata {

	private static final long serialVersionUID = 1L;

	@ToString
	private final Long sampleRate;

	@ToString
	private final int channelsCount;

	AudioStreamMetadataImpl(FFProbeMetadata ffp) {
		super(ffp.format, ffp.streams.audio);
		this.sampleRate =  TempleUtil.isEmpty(ffp.streams.audio.sample_rate) ? null : Long.valueOf(ffp.streams.audio.sample_rate);
		this.channelsCount = ffp.streams.audio.channels;
	}

	@Override
	public Long getSampleRate() {
		return this.sampleRate;
	}

	@Override
	public int getChannelsCount() {
		return this.channelsCount;
	}
}
