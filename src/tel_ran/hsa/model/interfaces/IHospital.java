package tel_ran.hsa.model.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;

import tel_ran.hsa.model.dto.*;

public interface IHospital {
	boolean addDoctor(Doctor doctor);
	boolean addPatient(Patient patient);
	boolean removeDoctor(int doctorId);
	boolean removePatient(int patientId);
	boolean updateDoctor(Doctor doctor);
	boolean updatePatient(Patient patient);
	Doctor getDoctor(int docotrId);
	Patient getPatient(int patientId);
	
	Iterable<Visit> buildSchedule(LocalDate startDate, LocalDate finishDate);
	boolean bookVisit(Doctor doctor, Patient patient, LocalDateTime dateTime);
	boolean cancelVisit(Doctor doctor, Patient patient, LocalDateTime dateTime);
	boolean cancelVisit(Patient patient, LocalDateTime dateTime);
	boolean cancelVisit(Doctor doctor, LocalDateTime dateTime);
	
	Iterable<Doctor> getPatientDoctors(int patientId);
	Iterable<Patient> getDoctorPatients(int docotrId);
	
	Iterable<Visit> getVisits(Patient patient, LocalDate beginDate, LocalDate endDate);
	Iterable<Visit> getVisits(Doctor goctor, LocalDate beginDate, LocalDate endDate);
	Iterable<Visit> getFreeVisits(Doctor goctor, LocalDate beginDate, LocalDate endDate);
	
	boolean addPulseInfo(Patient patient, LocalDateTime dateTime, int value);
	default Iterable<Integer> getPulseByPeriod(Patient patient, LocalDate beginDate, LocalDate endDate) {
		return getPulseByPeriod(patient, beginDate, endDate, patient.getHealthGroup().getServeyPeriod());
	}
	Iterable<Integer> getPulseByPeriod(Patient patient, LocalDate beginDate, LocalDate endDate, int surveyPeriod);
	
}
