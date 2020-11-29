package bank.template;

public class UserBranchTemplate {
	
	private String email;
	private String role;
	private String branchName;
	
	public UserBranchTemplate() {
	this.email = null;
	this.role = null;
	this.branchName = null;
	}
	
	
	public UserBranchTemplate(String email, String role, String branchName) {
		super();
		this.email = email;
		this.role = role;
		this.branchName = branchName;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	

}
