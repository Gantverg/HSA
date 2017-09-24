package tel_ran.hsa.protocols.api;

public interface RestResponseCode {
	String OK = "ok";
	String NO_DOCTOR = "no doctor";
	String NO_PATIENT = "no patient";
	String NO_SCHEDULE = "no schedule on this time";
	String VISIT_BUSY = "doctor at this time is busy";
	String ALREADY_EXIST = "already exist";
	String NO_HEALTH_GROUP = "no healthgroup";
	String VISIT_FREE = "visit free already";
}
