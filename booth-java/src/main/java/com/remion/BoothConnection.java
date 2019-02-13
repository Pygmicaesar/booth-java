package com.remion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoothConnection implements AutoCloseable {
	private static Logger logger = LoggerFactory.getLogger(BoothConnection.class);

	private BufferedReader bufferedReader;

	private BoothConnection() {
	}

	public static BoothConnection create(String host, int port) {
		logger.info("creating connection to {}:{}", host, port);
		return new BoothConnection(host, port);
	}

	private String host;
	private Integer port;
	private Socket socket;

	private BoothConnection(String host, int port) {
		if (host == null || host.isEmpty()) {
			throw new IllegalArgumentException("illegal host");
		}

		if (port < 0 || port > 65535) {
			throw new IllegalArgumentException("illegal port");
		}

		this.host = host;
		this.port = port;
	}

	public void open() {
		if (host == null || host.isEmpty()) {
			logger.warn("host is not set");
		}

		if (port == null) {
			logger.warn("port is not set");
		}

		logger.info("opening connection to {}:{}", host, port);

		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			throw new BoothConnectionException("opening connection to booth failed", e);
		}
	}

	public BufferedReader getLineReader() {

		if (bufferedReader == null) {
			try {
				if ( bufferedReader == null ) {
					bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				}

			} catch (IOException e) {
				bufferedReader = null;
				throw new BoothConnectionException("creating", e);
			}
		}

		return bufferedReader;
	}

	public void sendData(String data) {
		logger.info("sending data to booth {}:{}, {}", host, port, data);

		try {
			socket.getOutputStream().write(data.getBytes());
		} catch (IOException e) {
			throw new BoothConnectionException("sending data to booth failed", e);
		}
	}

	public void close() {
		if (socket != null && socket.isConnected()) {
			try {
				this.socket.close();
			} catch (IOException e) {
				throw new BoothConnectionException("closing connection to booth failed", e);
			} finally {
				this.socket = null;
				this.bufferedReader = null;
			}
		}
	}
}
