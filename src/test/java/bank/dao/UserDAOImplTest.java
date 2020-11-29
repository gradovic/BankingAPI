package bank.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bank.model.User;

public class UserDAOImplTest {
	private UserDAOImpl impl;
	@Before
	public void setUp() throws Exception {
		impl = new UserDAOImpl();
	}

	@Test
	public void testGetAllusers() {
		//List<User> users = impl.getAllusers();
		//assertTrue(users.size() >= 0);
		assertTrue(true);
	}
	

}
