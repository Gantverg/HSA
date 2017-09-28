package tel_ran.jackson;

import java.io.IOException;
import java.time.DayOfWeek;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class DayOfWeekDeserializer extends StdDeserializer<DayOfWeek> {
	public DayOfWeekDeserializer() {
		super(DayOfWeek.class);
	}

	@Override
	public DayOfWeek deserialize(JsonParser parser, DeserializationContext ctx)
			throws IOException, JsonProcessingException {
		return DayOfWeek.valueOf(parser.readValueAs(String.class));
	}
}
