package io.mountblue.learn.dao;

public class LoginDAO {
	private static final String USER_NAME="Swaroop";
	private static final String PASSWORD="1234";

	public boolean validateLoginDetails(String username, String password) {
		if(username==null || password==null) {
			return false;
		}
		else if(username.equals(USER_NAME) && password.equals(PASSWORD)) {
			return true;
		}
		else {
			return false;
		}	
	}
}
