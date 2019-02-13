package com.remion;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

class PortalJsonBuilder {

	public static String build(Instant timestamp, Map<String, Object> signals) {
		List<Object> values = new LinkedList<>();
		List<String> names = new LinkedList<>();

		for (Map.Entry<String, Object> e : signals.entrySet()) {
			names.add(e.getKey());
			values.add(e.getValue());
		}

		return new Gson().toJson(new SampleCollection(names, Collections.singletonList(new Sample(timestamp.toEpochMilli(), values))));
	}

	private static class SampleCollection {
		private List<String> names;
		private List<Sample> valueRows;

		public SampleCollection(List<String> names, List<Sample> valueRows) {
			this.names = names;
			this.valueRows = valueRows;
		}
	}

	private static class Sample {
		private Long timestamp;
		private List<Object> values;

		public Sample(Long timestamp, List<Object> values) {
			this.timestamp = timestamp;
			this.values = values;
		}
	}
}
