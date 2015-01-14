package com.temple.util.file.media.stream;

import java.io.File;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface StreamMetadataFactory {

	/**
	 * TODOC
	 *
	 * @param mediaFile
	 * @return
	 * @throws VideoStreamException if it is impossible to get video metadata from the file
	 */
	VideoMetadata getVideoMetadata(File mediaFile) throws VideoStreamException;
	/**
	 * TODOC
	 *
	 * @param mediaFile
	 * @return
	 */
	// AudioStreamMetadata getAudioMetadata(File mediaFile);
}
