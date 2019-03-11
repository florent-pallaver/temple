package com.temple.util.net.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import com.temple.util.net.HttpDomain;

public final class HTTPFileGetter extends AbstractHTTPGetter<File> {

	private static final long serialVersionUID = 1L;

	public static final int BUFFER_SIZE = 8192; // in bytes

	private final File downloadFolder;

	public HTTPFileGetter(HttpDomain<?> domain, File downloadFolder) {
		super(domain);
		this.downloadFolder = downloadFolder;
	}

	@Override
	protected File getFile0(HttpURLConnection con, String path) throws IOException {
		final byte[] cbuf = new byte[HTTPFileGetter.BUFFER_SIZE];
		File f = null;
		final String hf = con.getHeaderField("Content-Disposition");
		if (hf != null) {
			final int offset = hf.indexOf('=');
			if (offset > 0) {
				f = new File(this.downloadFolder, hf.substring(offset + 1));
			}
		}
		if (f == null) {
			f = File.createTempFile("temple_", "_download", this.downloadFolder);
		}
		try (DataInputStream is = new DataInputStream(con.getInputStream()); DataOutputStream os = new DataOutputStream(new FileOutputStream(f))) {
			int l = 0;
			while (l != -1) {
				l = is.read(cbuf);
				if (l > 0) {
					os.write(cbuf, 0, l);
				}
			}
		}
		return f;
	}
}
