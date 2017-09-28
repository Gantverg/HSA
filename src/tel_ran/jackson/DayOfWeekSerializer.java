package tel_ran.jackson;

import java.io.IOException;
import java.time.DayOfWeek;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


@SuppressWarnings("serial")
public class DayOfWeekSerializer extends StdSerializer<DayOfWeek> {

	protected DayOfWeekSerializer() {
		super(DayOfWeek.class);
	}

	@Override
	public void serialize(DayOfWeek dayOfWeek, JsonGenerator gen, SerializerProvider prov) throws IOException {
		gen.writeString(dayOfWeek.name());
		
	}

}
