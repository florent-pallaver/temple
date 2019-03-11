package com.temple.util.file.media.stream;

import java.io.File;

public interface StreamMetadataFactory {

	/**
	 * TODOC
	 *
	 * @param mediaFile
	 * @return
	 * @throws NoVideoStreamException if it is impossible to get video metadata from the file
	 */
	VideoMetadata getVideoMetadata(File mediaFile) throws NoVideoStreamException;
	/**
	 * TODOC
	 *
	 * @param mediaFile
	 * @return
	 */
	// AudioStreamMetadata getAudioMetadata(File mediaFile);
}
