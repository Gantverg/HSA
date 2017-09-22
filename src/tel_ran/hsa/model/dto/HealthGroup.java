package tel_ran.hsa.model.dto;

public class HealthGroup {
	String name;
	int minNormalPulse;
	int maxNormalPulse;
	int serveyPeriod;
	
	public HealthGroup(String name, int minNormalPulse, int maxNormalPulse, int serveyPeriod) {
		super();
		this.name = name;
		this.minNormalPulse = minNormalPulse;
		this.maxNormalPulse = maxNormalPulse;
		this.serveyPeriod = serveyPeriod;
	}
	public String getName() {
		return name;
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
	
	public void setName(String name) {
		this.name = name;
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
		return "HealthGroup [name=" + name + ", minNormalPulse=" + minNormalPulse + ", maxNormalPulse=" + maxNormalPulse
				+ ", serveyPeriod(minutes)=" + serveyPeriod + "]";
	}
	

}
