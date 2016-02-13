package com.temple.web.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.temple.util.file.media.Codec;

/**
 * Base servlet implementation class to serve a file
 */
public abstract class AbstractFileServlet extends AbstractTempleServlet {

	private static final long serialVersionUID = 1L;

	private final String mimeType;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param codec
	 */
	protected AbstractFileServlet(Codec codec) {
		super();
		this.mimeType = codec.getMimeType();
	}

	@Override
	protected final void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final File f = this.getFile(request);
		if (this.readable(f)) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(this.mimeType);
			response.setContentLengthLong(f.length());
		} else {
			if (this.isDebugLoggable()) {
				this.debug(f + " not readable");
			}
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		response.flushBuffer();
	}

	@Override
	protected final long getLastModified(HttpServletRequest req) {
		final File f = this.getFile(req);
		final long lm = this.readable(f) ? f.lastModified() : -1;
		return lm > 0 ? lm : -1;
	}

	@Override
	protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final File f = this.getFile(request);
		if (this.readable(f)) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(this.mimeType);
			response.setContentLengthLong(f.length());
			try (final ServletOutputStream sos = response.getOutputStream()) {
				Files.copy(f.toPath(), response.getOutputStream());
			} catch (final IOException e) {
				this.thrown(e);
			}
			response.flushBuffer();
		} else {
			if (this.isDebugLoggable()) {
				this.debug(f + " not readable");
			}
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private boolean readable(File f) {
		return f != null && f.canRead();
	}

	/**
	 * TODOC
	 *
	 * @param request
	 * @return
	 */
	protected abstract File getFile(HttpServletRequest request);
}
