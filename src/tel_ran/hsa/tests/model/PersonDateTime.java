package tel_ran.hsa.tests.model;

import java.time.LocalDateTime;

public class PersonDateTime {
	int personId;
	LocalDateTime dateTime;
	
	public PersonDateTime(int personId, LocalDateTime dateTime) {
		super();
		this.personId = personId;
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + personId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonDateTime other = (PersonDateTime) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (personId != other.personId)
			return false;
		return true;
	}
	
	
}
