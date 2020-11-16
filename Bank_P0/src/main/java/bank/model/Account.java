package bank.model;

import java.time.LocalDate;

public class Account {
 
	private int userID;
	private double balance;
	private String status;
	private LocalDate openDate;
	
	public LocalDate getOpenDate() {
		return openDate;
	}



	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}



	public Account(int userID, double balance, String status) {
		this.userID = userID;
		this.balance = balance;
		this.status = status;
		this.openDate = null;
	}
	
	

	public Account() {
		super();
		this.userID = -1;
		this.balance = 0;
		this.status = null;
		this.openDate = null;
				
	}



	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
