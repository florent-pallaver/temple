package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import com.temple.util.ToString;
import com.temple.util.file.media.stream.AudioStreamMetadata;
import com.temple.util.file.media.stream.VideoMetadata;
import com.temple.util.file.media.stream.VideoStreamMetadata;

final class VideoMetadataImpl extends AbstractStreamMetadata implements VideoMetadata {

	private static final long serialVersionUID = 1L;

	@ToString
	private final VideoStreamMetadata videoStreamMetadata;

	@ToString
	private final AudioStreamMetadata audioStreamMetadata;

	VideoMetadataImpl(FFProbeMetadata ffp) {
		super(ffp.format, ffp.format, true);
		// this.duration = videoStreamMetadata == null ? audioStreamMetadata.getDuration() :
		// videoStreamMetadata.getDuration();
		this.videoStreamMetadata = new VideoStreamMetadataImpl(ffp);
		this.audioStreamMetadata = new AudioStreamMetadataImpl(ffp);
	}

	@Override
	public VideoStreamMetadata getVideoStreamMetadata() {
		return this.videoStreamMetadata;
	}

	@Override
	public AudioStreamMetadata getAudioStreamMetadata() {
		return this.audioStreamMetadata;
	}
}
