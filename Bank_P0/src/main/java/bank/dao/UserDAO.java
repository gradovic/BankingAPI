package bank.dao;

import java.util.List;

import bank.model.User;

public interface UserDAO {
	
	List<User> getAllusers();
	boolean addUser(User add);
	boolean deleteUser(int userID);
	User getUserByEmail(String username);
	//boolean isAuthorized(String username, String password);
}
