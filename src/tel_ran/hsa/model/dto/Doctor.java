package tel_ran.hsa.model.dto;

import java.time.*;
import java.util.*;

public class Doctor extends Person {
	Set<DayOfWeek> workingDays;

	public Doctor(int id, String name, String phoneNumber, String eMail) {
		super(id, name, phoneNumber, eMail);
	}
	
	public void setWorkingDays(DayOfWeek... days) {
		workingDays = new HashSet<>(Arrays.asList(days));
	}

	public Iterable<DayOfWeek> getWorkingDays() {
		return workingDays;
	}
	
	public boolean isDayWorking(LocalDate date) {
		return workingDays.contains(date.getDayOfWeek());
	}
}
