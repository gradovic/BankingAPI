package bank.dao;

import java.util.List;

import bank.model.Branch;
import bank.model.User;

public class Test {
	
	
	public static void main(String[] args) {
		
		  BranchDAOImpl branchImpl = new BranchDAOImpl(); 
//		  Branch add = new Branch("nameFromIDE", "CityFromIDE");
		  List<User> users = branchImpl.getAllUsersByBranch(2);
		  //System.out.println(branchImpl.addBranch(add)); 
		  //List<Branch> branches = branchImpl.getAllBranches(); 
		  for(User user:users) {
		  System.out.println(user.getEmail()); 
		  }
		
	}

}

