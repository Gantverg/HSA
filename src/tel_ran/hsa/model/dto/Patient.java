package tel_ran.hsa.model.dto;

public class Patient extends Person {
	HealthGroup healthGroup;

	public Patient() {
	}

	public Patient(int id, String name, String phoneNumber, String eMail, HealthGroup healthGroup) {
		super(id, name, phoneNumber, eMail);
		this.healthGroup = healthGroup;
	}

	public HealthGroup getHealthGroup() {
		return healthGroup;
	}

	public void setHealthGroup(HealthGroup healthGroup) {
		this.healthGroup = healthGroup;
	}

}
