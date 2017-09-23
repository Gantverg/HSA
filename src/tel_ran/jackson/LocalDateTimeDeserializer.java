package tel_ran.jackson;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
	public LocalDateTimeDeserializer() {
		super(LocalDateTime.class);
	}

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctx)
			throws IOException, JsonProcessingException {
		return LocalDateTime.parse(parser.readValueAs(String.class));
	}
}
