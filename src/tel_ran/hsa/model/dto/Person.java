package tel_ran.hsa.model.dto;

abstract class Person {
	int id;
	String name;
	String phoneNumber;
	String eMail;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String geteMail() {
		return eMail;
	}

}
