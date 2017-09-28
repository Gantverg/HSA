package tel_ran.hsa.model.dto;

import java.time.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import tel_ran.jackson.WorkingDaysDeserializer;
import tel_ran.jackson.WorkingDaysSerializer;

public class Doctor extends Person {
	@JsonSerialize(using=WorkingDaysSerializer.class)
	@JsonDeserialize(using=WorkingDaysDeserializer.class)
	WorkingDays workingDays;

	public Doctor() {
	}
	
	public Doctor(int id, String name, String phoneNumber, String eMail) {
		super(id, name, phoneNumber, eMail);
	}
	
	public Iterable<DayOfWeek> getWorkingDays() {
		return workingDays == null ? null : workingDays.workDays;
	}
	
	public void setWorkingDays(WorkingDays workingDays) {
		this.workingDays = workingDays;
	}

	public boolean isDayWorking(LocalDate date) {
		return workingDays.workDays == null ? null : workingDays.workDays.contains(date.getDayOfWeek());
	}
}
