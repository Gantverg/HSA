package tel_ran.hsa.model.dto;

import java.time.LocalDateTime;

public class Visit {
	Doctor doctor;
	Patient patient;
	LocalDateTime dateTime;
	
	public Visit(Doctor doctor, Patient patient, LocalDateTime dateTime) {
		super();
		this.doctor = doctor;
		this.patient = patient;
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Visit [dateTime=" + dateTime + ", doctor=" + doctor + ", patient=" + patient + "]";
	}
	
}
