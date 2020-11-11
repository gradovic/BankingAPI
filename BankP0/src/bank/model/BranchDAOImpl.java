package bank.model;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import bank.utilities.*;
import bank.dao.BranchDAO;

public class BranchDAOImpl implements BranchDAO {
	Connection connection = null;	// Intialize the connection with null
	PreparedStatement stmt = null;	// to help protect against SQL injection

	@Override
	public List<Branch> getAllBranches() {
		// TODO Auto-generated method stub
		List<Branch> branches = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from branches";
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				Branch branch = new Branch();
				branch.setBranchName(result.getString("branchname"));
				branch.setBranchCity(result.getString("branchcity"));
				branches.add(branch);
			}
			result.close();
			
		}catch(SQLException e) {
			e.printStackTrace(); 
		}finally {
			
			closeResources();
		}
		
		// return the list of Book objects populated by the DB.
		return branches;
		
	}

	@Override
	public List<User> getAllUserByBranch(int branchID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addBranch(Branch branch) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
