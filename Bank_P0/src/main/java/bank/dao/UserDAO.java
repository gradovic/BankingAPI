package bank.dao;

import java.util.List;

import bank.model.User;

public interface UserDAO {
	
	List<User> getAllusers();
	boolean addUser(User add);
	boolean deleteUser(int userID);
	boolean updatePassword(String email, String newPassword);
	User getUserByEmail(String username);
	boolean updateProfileImage(int userID, byte[] image);
	byte[] getProfileImage(int userID);
}
