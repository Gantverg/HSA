package tel_ran.hsa.model;

import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import tel_ran.hsa.model.interfaces.IHospital;
import tel_ran.jackson.LocalTimeDeserializer;
import tel_ran.jackson.LocalTimeSerializer;

@SuppressWarnings("serial")
public abstract class Hospital implements IHospital {

	private static final LocalTime FINISH_TIME = LocalTime.of(18, 00);
	private static final LocalTime START_TIME = LocalTime.of(9, 00);
	@JsonSerialize(using=LocalTimeSerializer.class)
	@JsonDeserialize(using=LocalTimeDeserializer.class)
	protected LocalTime hospitalStartTime;
	@JsonSerialize(using=LocalTimeSerializer.class)
	@JsonDeserialize(using=LocalTimeDeserializer.class)
	protected LocalTime hospitalFinishTime;
	protected long timeSlot;
	
	public Hospital(String hospitalStartTime, String hospitalFinishTime, long timeSlot) {
		super();
		this.hospitalStartTime = LocalTime.parse(hospitalStartTime);
		this.hospitalFinishTime = LocalTime.parse(hospitalFinishTime);
		this.timeSlot = timeSlot;
	}
	public Hospital(long timeSlot) {
		super();
		this.hospitalStartTime = START_TIME;
		this.hospitalFinishTime = FINISH_TIME;
		this.timeSlot = timeSlot;
	}

	public LocalTime getHospitalStartTime() {
		return hospitalStartTime;
	}

	public LocalTime getHospitalFinishTime() {
		return hospitalFinishTime;
	}

	public long getTimeSlot() {
		return timeSlot;
	}

}
