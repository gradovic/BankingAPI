package bank.utilities;

import java.sql.SQLException;

import org.junit.Test;

public class DAOUtilitiesTest {

	@Test
	public void testGetConnection() throws SQLException {
		DAOUtilities.getConnection();
	}

}
