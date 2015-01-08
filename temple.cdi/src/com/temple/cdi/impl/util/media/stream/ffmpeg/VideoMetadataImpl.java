package com.temple.cdi.impl.util.media.stream.ffmpeg;

import com.temple.util.ToString;
import com.temple.util.media.stream.AudioStreamMetadata;
import com.temple.util.media.stream.VideoMetadata;
import com.temple.util.media.stream.VideoStreamMetadata;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
final class VideoMetadataImpl extends AbstractStreamMetadata implements VideoMetadata {

	private static final long serialVersionUID = 1L;

	@ToString
	private final VideoStreamMetadata videoStreamMetadata;

	@ToString
	private final AudioStreamMetadata audioStreamMetadata;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param codecName
	 * @param bitRate
	 * @param videoStreamMetadata
	 * @param audioStreamMetadata
	 */
	VideoMetadataImpl(FFProbeMetadata ffp) {
		super(ffp.format, ffp.format, true);
		// this.duration = videoStreamMetadata == null ? audioStreamMetadata.getDuration() :
		// videoStreamMetadata.getDuration();
		this.videoStreamMetadata = new VideoStreamMetadataImpl(ffp);
		this.audioStreamMetadata = new AudioStreamMetadataImpl(ffp);
	}

	/**
	 * @return the videoStreamMetadata
	 */
	@Override
	public VideoStreamMetadata getVideoStreamMetadata() {
		return this.videoStreamMetadata;
	}

	/**
	 * @return the audioStreamMetadata
	 */
	@Override
	public AudioStreamMetadata getAudioStreamMetadata() {
		return this.audioStreamMetadata;
	}
}
