package tel_ran.hsa.model.dto;

public class Patient extends Person{
	HealthGroup healthGroup;

	public HealthGroup getHealthGroup() {
		return healthGroup;
	}

	public void setHealthGroup(HealthGroup healthGroup) {
		this.healthGroup = healthGroup;
	}
	
	
}
