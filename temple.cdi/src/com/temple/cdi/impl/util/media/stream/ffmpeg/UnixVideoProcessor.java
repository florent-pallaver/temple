package com.temple.cdi.impl.util.media.stream.ffmpeg;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.temple.cdi.ImplementationStrategy;
import com.temple.cdi.OperatingSystem;
import com.temple.cdi.impl.AbstractCDIBean;
import com.temple.util.file.media.Codec;
import com.temple.util.file.media.MediaIndex;
import com.temple.util.file.media.image.ImageAppender;
import com.temple.util.file.media.image.ImageCodec;
import com.temple.util.file.media.image.ImageScaler;
import com.temple.util.file.media.image.MaxDimensionScaleStrategy;
import com.temple.util.file.media.stream.StreamMetadataFactory;
import com.temple.util.file.media.stream.VideoMetadata;
import com.temple.util.file.media.stream.VideoProcessor;
import com.temple.util.file.media.stream.VideoStreamException;
import com.temple.util.file.media.stream.VideoStreamMetadata;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@ApplicationScoped
@OperatingSystem(OperatingSystem.UNIX)
@ImplementationStrategy(ImplementationStrategy.FFMEG)
public class UnixVideoProcessor extends AbstractCDIBean implements VideoProcessor {

	@Inject
	@OperatingSystem(OperatingSystem.UNIX)
	@ImplementationStrategy(ImplementationStrategy.FFMEG)
	private StreamMetadataFactory smf;

	UnixVideoProcessor() {}

	@Override
	public void createPreviews(Serializable ownerId, Serializable videoId, MediaIndex previewIndex, File videoFile, int count, int maxDimension)
			throws VideoStreamException {
		final File dir = previewIndex.getDirectory(ownerId, videoId);
		final VideoMetadata vm = this.smf.getVideoMetadata(videoFile);
		final File[] screenShots = this.createThumbnails0(vm.getDuration(), videoFile.getAbsolutePath(), count, dir);
		final Codec mt = previewIndex.getMediaType();
		final ImageCodec imageType = ImageCodec.findType(mt.getMimeType());
		if (screenShots.length > 0 && imageType != null) {
			final ImageScaler scaler = imageType.getImageScaler();
			final File[] thumbnails = new File[screenShots.length];
			for (int i = screenShots.length; i-- > 0;) {
				final File f = screenShots[i];
				thumbnails[i] = new File(dir, previewIndex.getFileName(videoId, i));
				scaler.scale(f, thumbnails[i], maxDimension, MaxDimensionScaleStrategy.instance);
				this.delete(f);
			}
			try {
				final VideoStreamMetadata vsm = vm.getVideoStreamMetadata();
				if (vsm.getWidth() >= vsm.getHeight()) {
					ImageAppender.instance.appendHorizontally(thumbnails, new File(dir, previewIndex.getFileName(videoId)), imageType);
				} else {
					ImageAppender.instance.appendVertically(thumbnails, new File(dir, previewIndex.getFileName(videoId)), imageType);
				}
				// for (int i = 0; i < thumbnails.length; i++) {
				// this.delete(thumbnails[i]);
				// }
			} catch (final IOException e) {
				this.thrown(e);
			}
		}
	}

	private void delete(final File file) {
		if (!file.delete() && this.isWarningLoggable()) {
			this.warning("Unable to delete " + file.getAbsolutePath());
		}
	}

	private File[] createThumbnails0(int duration, String sourcePath, int count, File dir) throws VideoStreamException {
		final int f = duration / count;
		if (dir.exists() || dir.mkdirs()) {
			final String command = "/usr/bin/ffmpeg -ss " + f + " -i \"" + sourcePath + "\" -f image2 -vf fps=fps=1/" + f + ' ' + dir.getAbsolutePath()
					+ "/%d.png";
			this.info(command);
			final ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", command);
			try {
				final Process p = pb.start();
				final int termination = p.waitFor();
				if (termination == 0) {
					final File[] screenShots = new File[count];
					for (int i = count; i-- > 0;) {
						// TODO donner des noms plus uniques ...
						screenShots[i] = new File(dir, i + 1 + ".png");
					}
					return screenShots;
				} else {
					this.error(pb.command() + " failed with exit code " + termination);
				}
			} catch (final IOException | InterruptedException e) {
				this.thrown(e);
			}
		} else {
			this.error(dir.getAbsolutePath() + " does not exist and unable to create it");
		}
		return new File[0];
	}

	@Override
	public void convertVideo(Serializable ownerId, Serializable videoId, MediaIndex destVideoIndex, int maxDimension) {
		// TODO Auto-generated method stub
	}
}
