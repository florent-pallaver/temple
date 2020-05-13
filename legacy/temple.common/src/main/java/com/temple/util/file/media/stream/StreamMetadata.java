package com.temple.util.file.media.stream;

import java.io.Serializable;

public interface StreamMetadata extends Serializable {

	String getCodecName();

	String getCodecLongName();

	Integer getBitRate();

	Double getPreciseDuration();

	Integer getDuration();

	Integer getSize();
}
