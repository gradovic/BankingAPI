package bank.model;

import java.time.LocalDate;

public class User {
	

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate DOB;
	
	public User(String firstName, String lastName, String email, String password, LocalDate DOB) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.DOB = DOB;
	}
	
	public User() {
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.password = null;
		this.DOB = null;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDOB() {
		return DOB;
	}
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	
	

}
