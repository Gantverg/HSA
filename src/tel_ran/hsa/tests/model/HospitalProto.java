package tel_ran.hsa.tests.model;

import java.time.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import tel_ran.hsa.model.Hospital;
import tel_ran.hsa.model.dto.*;
import tel_ran.hsa.protocols.api.RestResponseCode;

@SuppressWarnings("serial")
public class HospitalProto extends Hospital {
	Map<Integer, Doctor> doctors;
	Map<Integer, Patient> patients;
	Map<Integer, HealthGroup> healthGroups;
	Map<PersonDateTime, Visit> schedule;
	Map<PersonDateTime, HeartBeat> pulseInfo;
	Map<Integer, WorkingDays> workingDaysList;

	public HospitalProto(
			String hospitalStartTime, 
			String hospitalFinishTime, 
			long timeSlot) {
		super(hospitalStartTime, hospitalFinishTime, timeSlot);
		doctors = new HashMap<>();
		patients = new HashMap<>();
		healthGroups = new HashMap<>();
		schedule = new HashMap<>();
		pulseInfo = new HashMap<>();
		fillWorkingDaysByDefault();

	}

	private void fillWorkingDaysByDefault() {
		DayOfWeek[][] scheduleVariants = {
				{DayOfWeek.SUNDAY,
				 DayOfWeek.MONDAY,
				 DayOfWeek.TUESDAY,
				 DayOfWeek.WEDNESDAY,
				 DayOfWeek.THURSDAY},
				{DayOfWeek.SUNDAY,
				 DayOfWeek.MONDAY,
				 DayOfWeek.WEDNESDAY,
				 DayOfWeek.THURSDAY,
				 DayOfWeek.FRIDAY},
				{DayOfWeek.SUNDAY,
				 DayOfWeek.TUESDAY,
				 DayOfWeek.THURSDAY,
				 DayOfWeek.SATURDAY}
				};
		workingDaysList = new HashMap<>();
		for(int daysId = 0; daysId < scheduleVariants.length; daysId++) {
			WorkingDays schedule = new WorkingDays(daysId);
			schedule.setWorkDays(scheduleVariants[daysId]);
			workingDaysList.put(daysId, schedule);
		}
		healthGroups = new HashMap<>();
		healthGroups.put(0, new HealthGroup(0, "Normal", 40, 80, 60*6));
		healthGroups.put(1, new HealthGroup(1, "Risk1", 80, 120, 30));
		healthGroups.put(2, new HealthGroup(2, "Risk2", 20, 60, 60));
		healthGroups.put(3, new HealthGroup(3, "Spies", 55, 65, 60));
	}

	@Override
	public String addDoctor(Doctor doctor) {
		if(doctors.containsKey(doctor.getId()))
			return RestResponseCode.ALREADY_EXIST;
		doctors.put(doctor.getId(), doctor);
		return RestResponseCode.OK;
	}

	@Override
	public String addPatient(Patient patient) {
		return patients.putIfAbsent(patient.getId(), patient) == null ? RestResponseCode.OK
				: RestResponseCode.ALREADY_EXIST;
	}

	@Override
	public String removeDoctor(int doctorId) {
		return doctors.remove(doctorId) == null ? RestResponseCode.NO_DOCTOR : RestResponseCode.OK;
	}

	@Override
	public String removePatient(int patientId) {
		return patients.remove(patientId) == null ? RestResponseCode.NO_PATIENT : RestResponseCode.OK;
	}

	@Override
	public String updateDoctor(Doctor doctor) {
		if(doctors.containsKey(doctor.getId())) {
			doctors.put(doctor.getId(), doctor);
			return RestResponseCode.OK;
		} else {
			return RestResponseCode.NO_DOCTOR;
		}
	}

	@Override
	public String updatePatient(Patient patient) {
		if(patients.containsKey(patient.getId())) {
			patients.put(patient.getId(), patient);
			return RestResponseCode.OK;
		} else {
			return RestResponseCode.NO_PATIENT;
		}
	}

	@Override
	public Doctor getDoctor(int doctorId) {
		return doctors.get(doctorId);
	}

	@Override
	public Patient getPatient(int patientId) {
		return patients.get(patientId);
	}

	@Override
	public Iterable<Visit> buildSchedule(LocalDate startDate, LocalDate finishDate) throws ScheduleNotEmptyException {
		Map<PersonDateTime,Visit> prevSchedule = new HashMap<>();
		prevSchedule.putAll(schedule);
		List<Visit> result = new ArrayList<>();
		for (Doctor doctor : doctors.values()) {
			for(LocalDate currentDate = startDate; 
					currentDate.isBefore(finishDate) ||currentDate.isEqual(finishDate); 
					currentDate = currentDate.plusDays(1)) {
				if(doctor.isDayWorking(currentDate))
					for (LocalTime currentTime = hospitalStartTime; 
							currentTime.isBefore(hospitalFinishTime); 
							currentTime = currentTime.plusMinutes(timeSlot)) {
						LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);
						Visit visit = new Visit(doctor, null, currentDateTime);
						if(schedule.put(new PersonDateTime(doctor.getId(), currentDateTime), visit) != null)
							throw new ScheduleNotEmptyException(doctor.toString()+
									" already has records to "+currentDateTime.toString());
						result.add(visit);
					}
			}
		}
		return result;
	}

	@Override
	public String bookVisit(int doctorId, int patientId, LocalDateTime dateTime) {
		if (!doctors.containsKey(doctorId))
			return RestResponseCode.NO_DOCTOR;
		Patient patient = patients.get(patientId);
		if (patient == null)
			return RestResponseCode.NO_PATIENT;
		PersonDateTime doctorDateTime = new PersonDateTime(doctorId, dateTime);
		if (!schedule.containsKey(doctorDateTime))
			return RestResponseCode.NO_SCHEDULE;
		Visit visit = schedule.get(doctorDateTime);
		if (visit.getPatient() != null)
			return RestResponseCode.VISIT_BUSY;
		visit.setPatient(patient);
		schedule.put(doctorDateTime, visit);
		return RestResponseCode.OK;
	}

	@Override
	public String cancelVisit(int doctorId, int patientId, LocalDateTime dateTime) {
		if (!doctors.containsKey(doctorId))
			return RestResponseCode.NO_DOCTOR;
		Patient patient = patients.get(patientId);
		if (patient == null)
			return RestResponseCode.NO_PATIENT;
		PersonDateTime doctorDateTime = new PersonDateTime(doctorId, dateTime);
		if (!schedule.containsKey(doctorDateTime))
			return RestResponseCode.NO_SCHEDULE;
		Visit visit = schedule.get(doctorDateTime);
		if (visit.getPatient() == null)
			return RestResponseCode.VISIT_FREE;
		if (visit.getPatient() != patient)
			return RestResponseCode.VISIT_BUSY;
		visit.setPatient(null);
		return RestResponseCode.OK;
	}

	@Override
	public String replaceVisitsDoctor(int oldDoctorId, int newDoctorId, LocalDateTime beginDateTime,
			LocalDateTime endDateTime) {
		if (!doctors.containsKey(oldDoctorId))
			return RestResponseCode.NO_DOCTOR;
		if (!doctors.containsKey(newDoctorId))
			return RestResponseCode.NO_DOCTOR;
		Doctor newDoctor = doctors.get(newDoctorId);

		schedule.entrySet().stream().filter(entry -> entry.getKey().personId == oldDoctorId)
				.filter(entry -> entry.getKey().dateTime.isAfter(beginDateTime)
						&& entry.getKey().dateTime.isBefore(endDateTime))
				.forEach(entry -> {
					entry.getKey().personId = newDoctorId;
					entry.getValue().setDoctor(newDoctor);
				});
		return RestResponseCode.OK;
	}

	@Override
	public Iterable<Doctor> getPatientDoctors(int patientId) {
		return schedule.values().stream().filter(v -> v.getPatient().getId() == patientId).map(Visit::getDoctor)
				.collect(Collectors.toSet());
	}

	@Override
	public Iterable<Patient> getDoctorPatients(int doctorId) {
		return schedule.entrySet().stream().filter(entry -> entry.getKey().personId == doctorId)
				.map(entry -> entry.getValue().getPatient()).collect(Collectors.toSet());
	}

	@Override
	public Iterable<Visit> getVisitsByPatient(int patientId, LocalDate beginDate, LocalDate endDate) {
		return schedule.entrySet().stream().filter(entry -> entry.getValue().getPatient().getId() == patientId)
				.filter(entry -> isVisitInPeriod(beginDate, endDate, entry))
				.map(entry -> entry.getValue()).collect(Collectors.toList());
	}

	private boolean isVisitInPeriod(LocalDate beginDate, LocalDate endDate, Entry<PersonDateTime, Visit> entry) {
		LocalDate date = entry.getKey().dateTime.toLocalDate();
		return date.isAfter(beginDate.minusDays(1)) && date.isBefore(endDate.plusDays(1));
	}

	@Override
	public Iterable<Visit> getVisitsByDoctor(int doctorId, LocalDate beginDate, LocalDate endDate) {
		return schedule.entrySet().stream().filter(entry -> entry.getKey().personId == doctorId)
				.filter(entry -> isVisitInPeriod(beginDate, endDate, entry))
				.filter(entry -> entry.getValue().getPatient() != null)
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<Visit> getFreeVisits(int doctorId, LocalDate beginDate, LocalDate endDate) {
		return schedule.entrySet().stream().filter(entry -> entry.getKey().personId == doctorId)
				.filter(entry -> isVisitInPeriod(beginDate, endDate, entry))
				.filter(entry -> entry.getValue().getPatient() == null)
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());
	}

	@Override
	public String addHealthGroup(HealthGroup healthGroup) {
		return healthGroups.putIfAbsent(healthGroup.getGroupId(), healthGroup) == null ? RestResponseCode.OK
						: RestResponseCode.ALREADY_EXIST;
	}

	@Override
	public String removeHealthGroup(int groupId) {
		return healthGroups.remove(groupId) == null ? RestResponseCode.NO_HEALTH_GROUP : RestResponseCode.OK;
	}

	@Override
	public Iterable<HealthGroup> getHealthGroups() {
		return healthGroups.values();
	}

	@Override
	public Iterable<Integer> getPulseByPeriod(int patientId, LocalDate beginDate, LocalDate endDate, int surveyPeriod) {
		return pulseInfo.entrySet().stream().filter(entry -> entry.getKey().personId == patientId)
				.filter(entry -> entry.getValue().getDateTime().isAfter(beginDate.atStartOfDay())
						&& entry.getValue().getDateTime().isBefore(beginDate.atStartOfDay()))
				.map(entry -> entry.getValue().getValue()).collect(Collectors.toList());
	}

	@Override
	public String addPulseInfo(int patientId, LocalDateTime dateTime, int value) {
		Patient patient = patients.get(patientId);
		if (patient == null)
			return RestResponseCode.NO_PATIENT;

		return pulseInfo.putIfAbsent(new PersonDateTime(patientId, dateTime),
				new HeartBeat(patient, dateTime, value, patient.getHealthGroup().getSurveyPeriod())) == null
						? RestResponseCode.OK
						: RestResponseCode.ALREADY_EXIST;
	}
	
	@Override
	public Iterable<Patient> getPatients() {
		return patients.values();
	}

	@Override
	public Iterator<Doctor> iterator() {
		return doctors.values().iterator();
	}

	@Override
	public String addWorkingDays(WorkingDays workingDays) {
		if(workingDaysList.containsKey(workingDays.getDaysId()))
			return RestResponseCode.ALREADY_EXIST;
		workingDaysList.put(workingDays.getDaysId(), workingDays);
		return RestResponseCode.OK;
	}

	@Override
	public String removeWorkingDays(int daysId) {
		if(!workingDaysList.containsKey(daysId))
			return RestResponseCode.NO_SCHEDULE;
		workingDaysList.remove(daysId);
		return RestResponseCode.OK;
	}

	@Override
	public WorkingDays getWorkingDays(int daysId) {
		return workingDaysList.get(daysId);
	}

	@Override
	public String setWorkingDays(int doctorId, int daysId) {
		Doctor doctor= doctors.get(doctorId);
		if(doctor == null)
			return RestResponseCode.NO_DOCTOR;
		WorkingDays schedule = workingDaysList.get(daysId);
		if(schedule == null)
			return RestResponseCode.NO_SCHEDULE;
		doctor.setWorkingDays(schedule);
		return RestResponseCode.OK;
	}

}
