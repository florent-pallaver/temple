package com.temple.util.file.media;

import java.io.File;
import java.io.Serializable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface MediaIndex {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	Codec getMediaType();

	/**
	 * TODOC
	 *
	 * @param mediaId
	 * @return
	 */
	default String getFileName(Serializable mediaId) {
		return this.getFileName(mediaId, null);
	}

	/**
	 * TODOC
	 *
	 * @param mediaId
	 * @param qualifier
	 * @return
	 */
	String getFileName(Serializable mediaId, Serializable qualifier);

	/**
	 * TODOC
	 *
	 * @param mediaId
	 * @return
	 */
	default File getFile(Serializable mediaId) {
		return this.getFile(null, mediaId, null);
	}

	/**
	 * TODOC
	 *
	 * @param ownerId
	 * @param mediaId
	 * @return
	 */
	default File getFile(Serializable ownerId, Serializable mediaId) {
		return this.getFile(ownerId, mediaId, null);
	}

	/**
	 * TODOC
	 *
	 * @param ownerId
	 * @param mediaId
	 * @param qualifier
	 * @return
	 */
	default File getFile(Serializable ownerId, Serializable mediaId, Serializable qualifier) {
		return new File(this.getDirectory(ownerId, mediaId), this.getFileName(mediaId, qualifier));
	}

	/**
	 * TODOC
	 *
	 * @param mediaId
	 * @return
	 */
	default File getDirectory(Serializable mediaId) {
		return this.getDirectory(null, mediaId);
	}

	/**
	 * TODOC
	 *
	 * @param ownerId
	 * @param mediaId
	 * @return
	 */
	File getDirectory(Serializable ownerId, Serializable mediaId);
}
