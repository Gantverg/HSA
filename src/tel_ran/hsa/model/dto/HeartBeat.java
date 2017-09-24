package tel_ran.hsa.model.dto;

import java.time.LocalDateTime;

public class HeartBeat {
	Patient patient;
	LocalDateTime dateTime;
	int value;
	int surveyPeriod;
	
	public HeartBeat(Patient patient, LocalDateTime dateTime, int value, int surveyPeriod) {
		super();
		this.patient = patient;
		this.dateTime = dateTime;
		this.value = value;
		this.surveyPeriod = surveyPeriod;
	}

	public Patient getPatient() {
		return patient;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public int getValue() {
		return value;
	}

	public int getSurveyPeriod() {
		return surveyPeriod;
	}

	@Override
	public String toString() {
		return "HeartBeat [patient=" + patient + ", dateTime=" + dateTime + ", value=" + value + ", surveyPeriod="
				+ surveyPeriod + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
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
		HeartBeat other = (HeartBeat) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}

}
