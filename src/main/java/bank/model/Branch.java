package bank.model;

public class Branch {
	
	
	private String branchName;
	private String branchCity;
	
	
	
	public Branch (String branchName, String branchCity) {
		this.branchName = branchName;
		this.branchCity = branchCity;
	}
	
	public Branch() {
		this.branchName = null;
		this.branchCity = null;
	}
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCity() {
		return branchCity;
	}
	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}
	
	

}
