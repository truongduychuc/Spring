package SpringMVCWeb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Accounts")
public class Account implements Serializable {
	private static final long serialVersionUID= -2054386655979281969L;
	
	public static final String ROLE_ADMIN="ADMIN"; //use for authentication
	public static final String ROLE_MANAGER="MANAGER";
	@Id
	@Column(name="User_Name",length=20,nullable=false)
	private String userName;
	
	@Column(name="Password",length=20,nullable=false)
	private String password;
	
	@Column(name="Active",length=1,nullable=false)
	private boolean active;
	
	@Column(name="User_Role")
	private String userRole;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "["+this.userName+","+this.password+","+this.userRole+"]";
	}
}
