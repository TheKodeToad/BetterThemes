package io.toadlabs.betterthemes.utils;

import java.io.*;
import java.net.*;
import java.nio.charset.*;

import org.osgi.service.url.*;

public abstract class ByteArrayURLStreamHandlerService extends AbstractURLStreamHandlerService {

	@Override
	public final URLConnection openConnection(URL url) throws IOException {
		byte[] bytes = getBytes(url);

		if(bytes == null) {
			return null;
		}

		return new ByteArrayURLConnection(url, bytes);
	}

	protected String getString(URL url) throws IOException {
		return null;
	}

	protected byte[] getBytes(URL url) throws IOException {
		String string = getString(url);
		return string == null ? null : string.getBytes(StandardCharsets.UTF_8);
	}

}
