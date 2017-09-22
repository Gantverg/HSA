package tel_ran.hsa.protocols.api;

public interface RestRequest {
	String DOCTOR_ADD = "/doctor/add";
	String DOCTOR_REMOVE = "/doctor/remove";
	String DOCTOR_GET = "/doctor/get";
	String DOCTOR_UPDATE = "/doctor/update";
	
	String PATIENT_ADD = "/patient/add";
	String PATIENT_REMOVE = "/patient/remove";
	String PATIENT_GET = "/patient/get";
	String PATIENT_UPDATE = "/patient/update";
	
	String VISIT_BOOK = "/visit/book";
	String VISIT_CANCEL = "/visit/cancel";
	String VISIT_CANCEL_DOCTOR = "/visit/cancel/doctor";
	String VISIT_CANCEL_PATIENT = "/visit/cancel/patient";
	
	String SCHEDULE_BUILD = "/schedule/build";
	String SCHEDULE_GET = "/schedule/get";
	
	String VISITS_GET_FREE = "/visits/get/doctor/free";
	String VISITS_GET_DOCTOR = "/visits/get/doctor";
	String VISITS_GET_PATIENT = "/visits/get/patient";
	String DOCTORS_GET = "/doctors/get";
	String PATIENTS_GET = "/patients/get";
	String DOCTORS_PATIENT_GET = "/doctors/patient/get";
	String PATIENTS_DOCTOR_GET = "/patients/doctor/get";
	
	String PULSE_ADD = "/pulse/add";
	String PULSE_GET = "/pulse/get";
	
}
