/**
 * 
 */
package services;

import interfaces.IAuthService;
import model.user.User;
import store.AuthStore;

/**
 * {@link AuthService} is an abstract class that implements the {@link IAuthService}
 * interface. It provides authentication functions for login and logout.
 */
public abstract class AuthService implements IAuthService {

	public abstract boolean login(String userID, String password);
	
	/**
	 * Constructs an instance of the {@link AuthService} class
	 */
	public AuthService() {}


	@Override
	public boolean logout() {
		AuthStore.setCurrentUser(null);
		return true;
	}

	/**
	 * Authenticate the user with password
	 * 
	 * @param user		user to be authenticated
	 * @param password	password for user to be authenticated
	 * @return			true if user's password matches input, false otherwise
	 */
	public boolean authenticate(User user, String password) {
		if (user == null)
			return false;
		if (!user.getPassword().equals(password))
			return false;
		return true;
	}
	
}
