package fr.esgi.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserManagerDBTest {
	
	private UserManagerDB dbManager;
	
	@Before
	public void setUp() throws Exception {
		dbManager = new UserManagerDB();
	}
	
	@Test
	public void testConnection() {
		Connection con;
		try {
			con = dbManager.getConnection();
			Assert.assertNotNull("DB connection should not be null", con);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e){
			Assert.fail("DB connection create should not throw an exception");
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCreateUser() {
		Boolean userCreated = dbManager.createUser("test_user", "test_pass");
		Assert.assertTrue("Should be able to create a user", userCreated);
		
		Boolean userDelete = dbManager.deleteUser("test_user");
		Assert.assertTrue("Should be able to create a user", userDelete);
	}
	
	@Test
	public void testGetAllUsers() {
		dbManager.createUser("test_user_1", "test_pass");
		List<User> userList = dbManager.allUsers();
		
		Assert.assertNotNull("The list should not be null", userList);
		Assert.assertFalse("The list should have at least one user", userList.isEmpty());
		
		Boolean userFound = false;
		for(User user : userList) {
			if(user.getLogin().equals("test_user_1") &&
			   user.getPassword().equals("test_pass")) {
				userFound = true;
				break;
			}
		}
		Assert.assertTrue("We should found the user just created", userFound);
		dbManager.deleteUser("test_user_1");
	}

}
