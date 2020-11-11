package bank.dao;

import java.util.List;

import bank.model.Branch;

public class Test {
	
	public static void main(String[] args) {
		BranchDAOImpl branchImpl = new BranchDAOImpl();
		List<Branch> branches = branchImpl.getAllBranches();
		for(Branch branch:branches) {
			System.out.println(branch.getBranchName());
		}
	}

}
