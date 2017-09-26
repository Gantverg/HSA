package tel_ran.security.entities;

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("serial")
public class Account implements Serializable{

	private String username;
	private String password;
	private Set<String> roles;

	public Account() {}

	public Account(String username, String password, Set<String> roles) {
		this.username = username;
		this.password = password;//Base64.getEncoder().encodeToString(password.getBytes());
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;//Base64.getEncoder().encodeToString(password.getBytes());
	}

	public Set<String> getRoles() {
		return roles;
	}

	public String[] getRolesAsArray() {
		return roles.stream().map(role->"ROLE_"+role).toArray(String[]::new);
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Account other = (Account) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=*****" + ", roles=" + roles + "]";
	}

}
