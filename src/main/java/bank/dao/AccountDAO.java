package bank.dao;

import java.util.List;

import bank.model.Account;

public interface AccountDAO {
	
	List<Account> getAllAccounts();
	List<Account> getAccountsPerUser(int UserID);
	boolean addAccount(Account add);
	boolean deleteAccount(int accountID);

}
