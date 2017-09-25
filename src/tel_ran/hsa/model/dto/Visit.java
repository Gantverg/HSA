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

	public Doctor getDoctor() {
		return doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visit other = (Visit) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		return true;
	}
	
}
