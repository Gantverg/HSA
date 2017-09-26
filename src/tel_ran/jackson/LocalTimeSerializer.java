package tel_ran.jackson;

import java.io.IOException;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
public class LocalTimeSerializer extends StdSerializer<LocalTime> {
	public LocalTimeSerializer() {
		super(LocalTime.class);
	}

	@Override
	public void serialize(LocalTime time, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(time.toString());
	}
}
