package tel_ran.hsa.model.dto;

public class HealthGroup {
	String groupName;
	int minNormalPulse;
	int maxNormalPulse;
	int serveyPeriod;
	
	public HealthGroup(String groupName, int minNormalPulse, int maxNormalPulse, int serveyPeriod) {
		super();
		this.groupName = groupName;
		this.minNormalPulse = minNormalPulse;
		this.maxNormalPulse = maxNormalPulse;
		this.serveyPeriod = serveyPeriod;
	}
	public String getGroupName() {
		return groupName;
	}
	public int getMinNormalPulse() {
		return minNormalPulse;
	}
	public int getMaxNormalPulse() {
		return maxNormalPulse;
	}
	public int getServeyPeriod() {
		return serveyPeriod;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setMinNormalPulse(int minNormalPulse) {
		this.minNormalPulse = minNormalPulse;
	}
	public void setMaxNormalPulse(int maxNormalPulse) {
		this.maxNormalPulse = maxNormalPulse;
	}
	public void setServeyPeriod(int serveyPeriod) {
		this.serveyPeriod = serveyPeriod;
	}
	
	@Override
	public String toString() {
		return "HealthGroup [name=" + groupName + ", minNormalPulse=" + minNormalPulse + ", maxNormalPulse=" + maxNormalPulse
				+ ", serveyPeriod(minutes)=" + serveyPeriod + "]";
	}
	

}
