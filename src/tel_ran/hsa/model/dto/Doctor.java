package tel_ran.hsa.model.dto;

import java.time.*;

public class Doctor extends Person {
	WorkingDays workingDays;

	public Doctor() {
	}
	
	public Doctor(int id, String name, String phoneNumber, String eMail) {
		super(id, name, phoneNumber, eMail);
	}
	
	public Iterable<DayOfWeek> getWorkingDays() {
		return workingDays.workDays;
	}
	
	public void setWorkingDays(WorkingDays workingDays) {
		this.workingDays = workingDays;
	}

	public boolean isDayWorking(LocalDate date) {
		return workingDays.workDays.contains(date.getDayOfWeek());
	}
}
