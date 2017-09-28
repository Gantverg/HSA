package tel_ran.hsa.model.dto;

import java.time.DayOfWeek;
import java.util.*;

import com.fasterxml.jackson.databind.annotation.*;

import tel_ran.jackson.*;

public class WorkingDays {
	int daysId;
	//@JsonSerialize(using=DayOfWeekSerializer.class)
	//@JsonDeserialize(using=DayOfWeekDeserializer.class)
	Set<DayOfWeek> workDays;

	public WorkingDays(int daysId) {
		this.daysId = daysId;
		workDays = new HashSet<>();
	}

	public int getDaysId() {
		return daysId;
	}

	public Set<DayOfWeek> getWorkDays() {
		return workDays;
	}

	public void setWorkDays(Set<DayOfWeek> workDays) {
		this.workDays = workDays;
	}

	public void setWorkDays(DayOfWeek... days) {
		for (DayOfWeek dayOfWeek : days) {
			workDays.add(dayOfWeek);
		}
	}

}
