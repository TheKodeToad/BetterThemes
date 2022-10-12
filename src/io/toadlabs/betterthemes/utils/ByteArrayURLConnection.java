package io.toadlabs.betterthemes.utils;

import java.io.*;
import java.net.*;

public final class ByteArrayURLConnection extends URLConnection {

	private final byte[] bytes;
	private InputStream in;

	public ByteArrayURLConnection(URL url, byte[] bytes) {
		super(url);
		this.bytes = bytes;
	}

	@Override
	public void connect() throws IOException {
		in = new ByteArrayInputStream(bytes);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		connect();
		return in;
	}

	@Override
	public int getContentLength() {
		return bytes.length;
	}

	@Override
	public long getContentLengthLong() {
		return getContentLength();
	}

}
