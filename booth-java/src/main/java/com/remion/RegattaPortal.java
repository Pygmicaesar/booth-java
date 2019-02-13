package com.remion;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

import com.google.common.util.concurrent.RateLimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RegattaPortal {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final OkHttpClient client = new OkHttpClient();
	private final RateLimiter rateLimiter = RateLimiter.create(1);

	private final String host;
	private final int port;
	private final String apikey;

	public RegattaPortal(String host, int port, String apikey) {
		this.host = host;
		this.port = port;
		this.apikey = apikey;
	}

	public void send(Instant timestamp, Map<String, Object> signals) {
		if (!rateLimiter.tryAcquire()) {
			return;
		}

		logger.info("sending to Regatta Portal, time = {}, data = {}", timestamp, signals);

		if (signals.isEmpty()) {
			logger.info("nothing to send");
			return;
		}

		String body = PortalJsonBuilder.build(timestamp, signals);

		HttpUrl url = new HttpUrl.Builder().host(host)
				.port(port)
				.scheme("https")
				.addPathSegment("regattaportal")
				.addPathSegment("v1")
				.addPathSegment("device")
				.addPathSegment("data")
				.addQueryParameter("apikey",apikey)
				.build();

		Request request = new Request.Builder().url(url)
				.post(RequestBody.create(MediaType.parse("application/json"), body))
				.build();

		try {
			client.newCall(request).execute();
		} catch (IOException e) {
			logger.warn("sending data to Regatta Portal failed", e);
		}
	}
}
