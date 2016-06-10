package fr.esgi.model;

import java.util.ArrayList;

public class UserManagerFromDisk extends UserManagerInMemory {

	public  UserManagerFromDisk() {
		super();
		this.userList = loadUsersFromDisk();
	}
	
	@Override
	public boolean createUser(String login, String password) {
		boolean added = super.createUser(login, password);
		if (added) {
			saveToDisk();
		}
		return added;
	}
	
	public void saveToDisk() {
		
	}
	
	public ArrayList<User> loadUsersFromDisk(){
		return null;
	}
}
