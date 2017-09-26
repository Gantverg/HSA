package tel_ran.jackson;

import java.io.IOException;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class LocalTimeDeserializer extends StdDeserializer<LocalTime> {
	public LocalTimeDeserializer() {
		super(LocalTime.class);
	}

	@Override
	public LocalTime deserialize(JsonParser parser, DeserializationContext ctx)
			throws IOException, JsonProcessingException {
		return LocalTime.parse(parser.readValueAs(String.class));
	}
}
