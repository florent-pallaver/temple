package com.temple.cdi.impl.util.media.stream.ffmpeg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonReader;

import com.temple.cdi.ImplementationStrategy;
import com.temple.cdi.OperatingSystem;
import com.temple.cdi.impl.AbstractCDIBean;
import com.temple.util.file.media.stream.StreamMetadataFactory;
import com.temple.util.file.media.stream.VideoMetadata;
import com.temple.util.file.media.stream.VideoStreamException;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@ApplicationScoped
@OperatingSystem(OperatingSystem.UNIX)
@ImplementationStrategy(ImplementationStrategy.FFMEG)
public class UnixStreamMetadataFactory extends AbstractCDIBean implements StreamMetadataFactory {

	UnixStreamMetadataFactory() {}

	@Override
	public VideoMetadata getVideoMetadata(File mediaFile) throws VideoStreamException {
		final FFProbeMetadata metadata = this.getMetadata(mediaFile);
		if (this.isDebugLoggable()) {
			this.debug(metadata);
		}
		if (metadata.streams.video == null) {
			throw new VideoStreamException(mediaFile);
		}
		return new VideoMetadataImpl(metadata);
	}

	private FFProbeMetadata getMetadata(File mediaFile) {
		final ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "/usr/bin/ffprobe -v quiet -of json=compact=1 -show_streams -show_format -i \""
				+ mediaFile.getAbsolutePath() + '"');
		JsonReader r;
		File stat = null;
		try {
			stat = File.createTempFile("ffprobe", "metadata");
			pb.redirectOutput(stat);
			final Process p = pb.start();
			final int termination = p.waitFor();
			if (termination == 0) {
				r = Json.createReader(new FileInputStream(stat));
				final FFProbeMetadata pm = new FFProbeMetadata(r.readObject());
				pm.format.size = (int) mediaFile.length();
				return pm;
			} else {
				this.error(pb.command() + " failed with exit code " + termination);
			}
		} catch (final IOException | InterruptedException e) {
			this.thrown(e);
		} finally {
			if (stat != null) {
				stat.delete();
			}
		}
		return null;
	}
}