package com.temple.util.net.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;

public interface HTTPGetter<F extends Serializable> {

	String HTTP_PROTOCOL = "http";

	String HTTPS_PROTOCOL = "https";

	int DEFAULT_CONNECT_TIMEOUT = 15000; // in milliseconds

	Cookie getCookie() ;

	F getFile(String fileURL) throws HTTPGetException ;

	int getTimeout();
	
	static File downloadFile(HttpURLConnection con, File downloadFolder) throws IOException {
		final byte[] cbuf = new byte[16384];
		File file = null;
		final String hf = con.getHeaderField("Content-Disposition");
		if (hf != null) {
			final int offset = hf.indexOf('=');
			if (offset > 0) {
				file = new File(downloadFolder, hf.substring(offset + 1));
			}
		}
		if (file == null) {
			file = File.createTempFile("temple_", "_download", downloadFolder);
		}
		try (DataInputStream is = new DataInputStream(con.getInputStream()); DataOutputStream os = new DataOutputStream(new FileOutputStream(file))) {
			for (int l = 0; l != -1;) {
				l = is.read(cbuf);
				if (l > 0) {
					os.write(cbuf, 0, l);
				}
			}
		}
		return file;
	}

	
}
