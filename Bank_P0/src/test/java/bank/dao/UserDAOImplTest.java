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
		List<User> users = impl.getAllusers();
		assertTrue(users.size() >= 0);
	}

	@Test
	public void testAddUser() {
		// because Email is configured to be unique on the database
		float randomEmail = (float) ((Math.random() * 10) + 1);
		User user = new User(2, "jUnitFirst", "jUnitLast", "jUnitEmail" + String.valueOf(randomEmail), "jUnitPass", LocalDate.of(1990, 6, 22));
		assertTrue(impl.addUser(user));
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

}
