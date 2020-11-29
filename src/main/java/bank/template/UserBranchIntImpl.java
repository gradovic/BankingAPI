package bank.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.utilities.DAOUtilities;

public class UserBranchIntImpl implements UserBranchInt {
	PreparedStatement stmt = null;

	@Override
	public List<UserBranchTemplate> getUsersBranchName() {
		List<UserBranchTemplate> userBranches = new ArrayList<>();
		try (Connection connection = DAOUtilities.getConnection()) {
			String sql = "select u.email, u.role, b.branchname from\r\n" + "users u\r\n" + "join\r\n" + "branches b\r\n"
					+ "on\r\n" + "u.branchid = b.branchid";
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				UserBranchTemplate userBranch = new UserBranchTemplate();
				userBranch.setBranchName(result.getString("branchname"));
				userBranch.setEmail(result.getString("email"));
				userBranch.setRole(result.getString("role"));
				userBranches.add(userBranch);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userBranches;
	}

}
