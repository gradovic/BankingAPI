package bank.model;

import java.time.LocalDate;

public class user {
	
	private int UserID;
	private int BranchID;
	private String firstName;
	private String lastName;
	private String Email;
	private String Password;
	private LocalDate DOB;
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getBranchID() {
		return BranchID;
	}
	public void setBranchID(int branchID) {
		BranchID = branchID;
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
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}

}
