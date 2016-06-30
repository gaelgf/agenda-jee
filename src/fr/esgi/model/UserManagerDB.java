package fr.esgi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManagerDB implements IUserManager {
	private Connection connection;
	
	public UserManagerDB() {
		try {
			this.connection =  this.getConnection();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/agenda-jee";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, "root", "");
		return connection;
	}

	@Override
	public boolean checkLogin(String login) {
		User u = this.getUser(login);
		return u!= null;
	}

	@Override
	public boolean checkLoginWithPassword(String login, String password) {
		User u = this.getUser(login);
		if(u != null) {
			return u.getPassword().equals(password);
		}
		return false;
	}

	@Override
	public boolean createUser(String login, String password) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			String userSQL ="INSERT INTO user(login, password) VALUES(?, ?);";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setString(1, login);
			stmt.setString(2, password);
			
			result = stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result != 0;
	}
	
	@Override
	public User getUser(String login) {
		User user = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			String userSQL = "SELECT * FROM user WHERE login = ?";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setString(1, login);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				String loginU = rs.getString("login");
				String password = rs.getString("password");
				user = new User(loginU, password);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user;
	}

	
	@Override
	public List<User> allUsers() {
		List<User> userList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.connection.createStatement();
			String userSQL = "SELECT * FROM  user;";
			rs = stmt.executeQuery(userSQL);
			while(rs.next()) {
				String login = rs.getString("login");
				String password = rs.getString("password");
				User newUser = new User(login, password);
				userList.add(newUser);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public boolean deleteUser(String login) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			String deleteUserSQL ="DELETE FROM user WHERE login = ?;";
			stmt = this.connection.prepareStatement(deleteUserSQL);
			stmt.setString(1, login);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result == 1;
	}

}