package bank.model;

import java.time.LocalDate;

public class User {
	
	private int userID;
	private int branchID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate DOB;
	private String role;

	public User(int branchID, String firstName, String lastName, String email, String password, LocalDate DOB,
			String role) {

		this.branchID = branchID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.DOB = DOB;
		this.role = role;
	}

	public User() {
		this.userID = -1;
		this.branchID = -1;
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.password = null;
		this.DOB = null;
		this.role = null;
	}
	
	

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
