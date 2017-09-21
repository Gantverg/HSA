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
	
	String SCHEDULE_BUILD = "/schedule/build";
	String SCHEDULE_GET = "/schedule/get";
	
	String VISITS_GET = "/visits/get";
	String DOCTORS_GET = "/doctors/get";
	String PATIENTS_GET = "/patients/get";
	
	String PULSE_ADD = "/pulse/add";
	String PULSE_GET = "/pulse/get";
	
}
