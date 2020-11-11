package bank.model;

public class branch {
	
	
	private String branchName;
	private String branchCity;
	
	
	
	public branch (String branchName, String branchCity) {
		this.branchName = branchName;
		this.branchCity = branchCity;
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
