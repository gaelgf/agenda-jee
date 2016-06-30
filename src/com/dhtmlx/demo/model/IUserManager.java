package com.dhtmlx.demo.model;

import java.util.List;

public interface IUserManager {
	public boolean checkLogin(String login);
	public boolean checkLoginWithPassword(String login, String password);
	public User getUser(String login);
	public boolean createUser(String login, String password);
	public List<User> allUsers();
}
