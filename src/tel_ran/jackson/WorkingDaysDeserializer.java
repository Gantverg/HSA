package tel_ran.jackson;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import tel_ran.hsa.model.dto.WorkingDays;

@SuppressWarnings("serial")
public class WorkingDaysDeserializer extends StdDeserializer<WorkingDays> {

	public WorkingDaysDeserializer() {
		super(WorkingDays.class);
	}

	@Override
	public WorkingDays deserialize(JsonParser parser, DeserializationContext ctx)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String stringBody = parser.readValueAs(String.class);
		System.out.println("stringBody="+stringBody);
		Map<String,String> body = mapper.readValue(stringBody, new TypeReference<Map<String,String>>() {});
		int daysId = Integer.parseInt(body.get("daysId"));
		WorkingDays result = new WorkingDays(daysId);
		Set<DayOfWeek> workDays = body.entrySet().stream()
			.filter(entry->entry.getKey().startsWith("dayOfWeek"))
			.map(entry->DayOfWeek.valueOf(entry.getValue()))
			.collect(Collectors.toSet());
		result.setWorkDays(workDays);
		return result;
	}

}
