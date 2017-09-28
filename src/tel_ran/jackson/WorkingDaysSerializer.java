package tel_ran.jackson;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import tel_ran.hsa.model.dto.WorkingDays;

@SuppressWarnings("serial")
public class WorkingDaysSerializer extends StdSerializer<WorkingDays> {
	public WorkingDaysSerializer() {
		super(WorkingDays.class);
	}

	@Override
	public void serialize(WorkingDays workingDays, JsonGenerator gen, SerializerProvider prov) throws IOException {
		Map<String,String> body = new HashMap<>();
		body.put("daysId", String.valueOf(workingDays.getDaysId()));
		for (DayOfWeek dayOfWeek : workingDays.getWorkDays()) {
			body.put("dayOfWeek"+dayOfWeek.getValue(), dayOfWeek.name());
		}
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("body="+mapper.writeValueAsString(body));
		gen.writeString(mapper.writeValueAsString(body));
	}
}
