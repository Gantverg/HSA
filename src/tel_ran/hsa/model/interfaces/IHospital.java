package tel_ran.hsa.model.interfaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import tel_ran.hsa.model.dto.*;
import tel_ran.hsa.protocols.api.RestResponseCode;

public interface IHospital extends Serializable, Iterable<Doctor>{
	String addDoctor(Doctor doctor);
	String addPatient(Patient patient);
	String removeDoctor(int doctorId);
	String removePatient(int patientId);
	String updateDoctor(Doctor doctor);
	String updatePatient(Patient patient);
	Doctor getDoctor(int doctorId);
	Patient getPatient(int patientId);
	
	Iterable<Visit> buildSchedule(LocalDate startDate, LocalDate finishDate);
	String bookVisit(int doctorId, int patientId, LocalDateTime dateTime);
	String cancelVisit(int doctorId, int patientId, LocalDateTime dateTime);
	String replaceVisitsDoctor(int oldDoctorId, int newDoctorId, LocalDateTime beginDateTime, LocalDateTime endDateTime);
	
	Iterable<Doctor> getPatientDoctors(int patientId);
	Iterable<Patient> getDoctorPatients(int doctorId);
	
	Iterable<Visit> getVisitsByPatient(int patientId, LocalDate beginDate, LocalDate endDate);
	Iterable<Visit> getVisitsByDoctor(int doctorId, LocalDate beginDate, LocalDate endDate);
	Iterable<Visit> getFreeVisits(int doctorId, LocalDate beginDate, LocalDate endDate);
	
	String addHealthGroup(HealthGroup healthGroup);
	String removeHealthGroup(int groupId);
	Iterable<HealthGroup> getHealthGroups();
	Iterable<Patient> getPatients();
	
	default String addPulseInfo(int patientId, LocalDateTime dateTime, int value) {
		return RestResponseCode.OK;
	}
	default Iterable<Integer> getPulseByPeriod(int patientId, LocalDate beginDate, LocalDate endDate) {
		return getPulseByPeriod(patientId, beginDate, endDate, getPatient(patientId).getHealthGroup().getSurveyPeriod());
	}
	Iterable<Integer> getPulseByPeriod(int patientId, LocalDate beginDate, LocalDate endDate, int surveyPeriod);
	
}
