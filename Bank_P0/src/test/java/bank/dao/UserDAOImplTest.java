package bank.dao;

import static org.junit.Assert.*;

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
		List<User> users = impl.getAllusers();
		assertTrue(users.size() >= 0);
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

}
