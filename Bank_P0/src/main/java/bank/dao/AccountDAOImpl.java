package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.model.Account;
import bank.utilities.DAOUtilities;

public class AccountDAOImpl implements AccountDAO {

	Connection connection = null;
	PreparedStatement stmt = null;

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from accounts";
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				Account account = new Account();
				account.setUserID(result.getInt("userid"));
				account.setBalance(result.getDouble("balance"));
				account.setStatus(result.getString("status"));
				account.setOpenDate(result.getDate("opendate").toLocalDate());
				accounts.add(account);
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return accounts;
	}

	@Override
	public List<Account> getAccountsPerUser(int UserID) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from accounts where userid =?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, UserID);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				Account account = new Account();
				account.setUserID(result.getInt("userid"));
				account.setBalance(result.getDouble("balance"));
				account.setOpenDate(result.getDate("opendate").toLocalDate());
				account.setStatus(result.getString("status"));
				accounts.add(account);
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return accounts;

	}

	@Override
	public boolean addAccount(Account add) {

		try {
			connection = DAOUtilities.getConnection();
			String sql = "insert into accounts (userid, balance, status) values (?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, add.getUserID());
			stmt.setDouble(2, add.getBalance());
			stmt.setString(3, add.getStatus());

			if (stmt.executeUpdate() != 0) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();

		}
	}

	@Override
	public boolean deleteAccount(int accountID) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "delete from accounts where accountid=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, accountID);
			if (stmt.executeUpdate() != 0) {
				return true;
			} else
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
