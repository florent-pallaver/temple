package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.json.AbstractJsonable;
import com.temple.util.json.JsonField;
import com.temple.util.json.JsonField.Handler;
import com.temple.util.json.JsonUtil;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
final class FFProbeMetadata extends AbstractJsonable {

	@ToString
	@JsonField(handler = FormatHandler.class, outputable = false)
	Format format;

	@ToString
	@JsonField(handler = StreamsHandler.class, outputable = false)
	Streams streams;

	FFProbeMetadata(JsonObject jo) {
		super(jo);
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}

	public final static class FormatHandler implements Handler {

		@Override
		public void add(JsonObjectBuilder job, String name, Object value) {
			// not implemented
			throw new RuntimeException("not implemented");
		}

		@Override
		public Object getValue(JsonObject jo, String name) {
			final Format f = new Format();
			JsonUtil.setValues(f, jo.getJsonObject(name));
			return f;
		}
	}

	public final static class StreamsHandler implements Handler {

		@Override
		public void add(JsonObjectBuilder job, String name, Object value) {
			// not implemented
			throw new RuntimeException("not implemented");
		}

		@Override
		public Object getValue(JsonObject jo, String name) {
			final Streams s = new Streams();
			final JsonArray ja = jo.getJsonArray(name);
			for (final JsonValue jv : ja) {
				final JsonObject jsonObject = (JsonObject) jv;
				switch (jsonObject.getString("codec_type")) {
				case "video":
					s.video = new VideoStream();
					JsonUtil.setValues(s.video, jsonObject);
					break;
				case "audio":
					s.audio = new AudioStream();
					JsonUtil.setValues(s.audio, jsonObject);
					break;
				default:
				}
			}
			return s;
		}
	}
}
