package fr.esgi.model;

import java.util.ArrayList;
import java.util.List;

public class UserManagerInMemory implements IUserManager {
	protected ArrayList<User> userList = new ArrayList<>();
	
	public UserManagerInMemory() {
		createUsers();
	}
	
	public void createUsers(){
		this.userList.add(new User("user1", "pass1"));
		this.userList.add(new User("user2", "pass2"));
		this.userList.add(new User("user3", "pass3"));
		this.userList.add(new User("user4", "pass4"));
	}
	
	@Override
	public boolean checkLogin(String login) {
		return (this.getUser(login) != null);
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
		if (!checkLogin(login)) {
			this.userList.add(new User(login, password));
			return true;
		}
		return false;
	}
	
	
	private User getUser(String login) {
		for(User u : this.userList) {
			if (u.getLogin().equals(login)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public List<User> allUsers() {
		return this.userList;
	}
}
