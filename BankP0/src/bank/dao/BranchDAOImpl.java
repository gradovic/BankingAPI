package bank.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import bank.model.Branch;
import bank.model.User;
import bank.utilities.*;

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
		try {
		connection = DAOUtilities.getConnection();
		String sql = "insert into branches (BranchName, BranchCity) values (? , ?)";
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, branch.getBranchName());
		stmt.setString(2, branch.getBranchCity());
		if (stmt.executeUpdate() != 0)
			return true;
		else
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
		
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
