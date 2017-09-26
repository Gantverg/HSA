package tel_ran.hsa.model.dto;

import java.time.DayOfWeek;
import java.util.*;

public class WorkingDays {
	Set<DayOfWeek> workDays;

	public WorkingDays() {
		workDays = new HashSet<>();
	}

	public void setDays(DayOfWeek... days) {
		for (DayOfWeek dayOfWeek : days) {
			workDays.add(dayOfWeek);
		}
	}

}
