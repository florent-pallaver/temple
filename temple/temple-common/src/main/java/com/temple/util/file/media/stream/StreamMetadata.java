package com.temple.util.file.media.stream;

import java.io.Serializable;

public interface StreamMetadata extends Serializable {

	String getCodecName();

	String getCodecLongName();

	int getBitRate();

	long getSize();
	
	double getPreciseDuration();

	default int getDuration() {
		return (int) Math.round(getPreciseDuration());
	}

	default boolean isEmpty() {
		return getSize() == 0 || getDuration() == 0;
	}
}
