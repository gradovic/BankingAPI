package bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.model.User;
import bank.utilities.DAOUtilities;

public class UserDAOImpl implements UserDAO {

	PreparedStatement stmt = null; // to help protect against SQL injection

	@Override
	public List<User> getAllusers() {
		List<User> users = new ArrayList<User>();
		try (Connection connection = DAOUtilities.getConnection()) {
			String sql = "select * from users";
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				User user = new User();
				user.setUserID(result.getInt("userid"));
				user.setBranchID(result.getInt("branchid"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("pass"));
				user.setDOB(result.getDate("dob").toLocalDate());
				user.setRole(result.getString("role"));

				users.add(user);
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public boolean addUser(User add) {

		try (Connection connection = DAOUtilities.getConnection()) {
			String sql = "insert into users (branchid, firstname, lastname, email, pass, dob, role) values (?, ?, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, add.getBranchID());
			stmt.setString(2, add.getFirstName());
			stmt.setString(3, add.getLastName());
			stmt.setString(4, add.getEmail());
			stmt.setString(5, add.getPassword());
			stmt.setDate(6, Date.valueOf(add.getDOB()));
			stmt.setString(7, add.getRole());

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(int userID) {

		try (Connection connection = DAOUtilities.getConnection()) {
			String sql = "delete from users where userid=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, userID);
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public User getUserByEmail(String username) {
		User user = new User();
		try (Connection connection = DAOUtilities.getConnection()) {
			String sql = "select * from users where email=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				user.setUserID(result.getInt("userid"));
				user.setBranchID(result.getInt("branchid"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("pass"));
				user.setDOB(result.getDate("dob").toLocalDate());
				user.setRole(result.getString("role"));
				return user;
			}

			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
