package bank.dao;

import java.util.List;

import bank.model.Branch;
import bank.model.BranchDAOImpl;

public class Test {
	
	public static void main(String[] args) {
		BranchDAOImpl branchImpl = new BranchDAOImpl();
		List<Branch> branches = branchImpl.getAllBranches();
		for(Branch branch:branches) {
			System.out.println(branch.getBranchCity());
		}
	}

}
