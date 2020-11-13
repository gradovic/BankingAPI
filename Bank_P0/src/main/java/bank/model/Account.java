package bank.model;

public class Account {
 
	private int userID;
	private String accountNumber;
	private double balance;
	private String status;
	
	public Account(int userID, String accountNumber, double balance, String status) {
		this.userID = userID;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.status = status;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
