package tel_ran.hsa.model.interfaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import tel_ran.hsa.model.dto.*;

public interface IHospital extends Serializable{
	boolean addDoctor(Doctor doctor);
	boolean addPatient(Patient patient);
	boolean removeDoctor(int doctorId);
	boolean removePatient(int patientId);
	boolean updateDoctor(Doctor doctor);
	boolean updatePatient(Patient patient);
	Doctor getDoctor(int docotrId);
	Patient getPatient(int patientId);
	
	Iterable<Visit> buildSchedule(LocalDate startDate, LocalDate finishDate);
	boolean bookVisit(int doctorId, int patientId, LocalDateTime dateTime);
	boolean cancelVisit(int doctorId, int patientId, LocalDateTime dateTime);
	boolean cancelVisitByPatient(int patientId, LocalDateTime dateTime);
	boolean cancelVisitByDoctor(int doctorId, LocalDateTime dateTime);
	
	Iterable<Doctor> getPatientDoctors(int patientId);
	Iterable<Patient> getDoctorPatients(int docotrId);
	
	Iterable<Visit> getVisitsByPatient(int patientId, LocalDate beginDate, LocalDate endDate);
	Iterable<Visit> getVisitsByDoctor(int doctorId, LocalDate beginDate, LocalDate endDate);
	Iterable<Visit> getFreeVisits(int doctorId, LocalDate beginDate, LocalDate endDate);
	
	boolean addPulseInfo(int patientId, LocalDateTime dateTime, int value);
	default Iterable<Integer> getPulseByPeriod(int patientId, LocalDate beginDate, LocalDate endDate) {
		return getPulseByPeriod(patientId, beginDate, endDate, getPatient(patientId).getHealthGroup().getServeyPeriod());
	}
	Iterable<Integer> getPulseByPeriod(int patientId, LocalDate beginDate, LocalDate endDate, int surveyPeriod);
	
}
