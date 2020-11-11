package bank.dao;
import java.util.List;

import bank.model.Branch;
import bank.model.User;;

public interface BranchDAO {
	
	public List<Branch> getAllBranches();
	public List<User> getAllUserByBranch(int branchID);
	public boolean addBranch(Branch branch);

}
